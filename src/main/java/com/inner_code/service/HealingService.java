package com.inner_code.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import lombok.Data;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Service

public class HealingService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(HealingService.class);
    @Value("${google.genai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public HealingService() {
    }

    public PersonalOverViewDto getHealingData(HealingRequest request) throws JsonProcessingException {
        String prompt = buildPrompt(request);

        System.out.println("prompt--> " + prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of("contents", List.of(
                Map.of("parts", List.of(Map.of("text", prompt)))
        ));

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(requestBody, headers);

        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, httpEntity, Map.class);

        String fullText = extractTextFromResponse(response.getBody());
        String jsonPart = extractJsonString(fullText);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonPart, PersonalOverViewDto.class);
    }

    private String buildPrompt(HealingRequest r) {
        return String.format("""
                You are a trauma-informed, astrology-aware healing guide.
                
                Input Data:
                - Birth Date: %s
                - Birth Time: %s
                - Birth Place: %s
                    According to the user BirthDate, BirthTime, and BirthPlace,
                
                    step1You have to calculate the User's Chiron Placement: Chiron in zodiac_sign in the house.
                
                    🗣️ Language Preference: Please generate the entire response in **%s**.
                
                    Translate gently and contextually — not word-for-word — while keeping the meaning, warmth, and emotional depth.
                    Ensure the tone remains poetic, empathetic, trauma-sensitive, and culturally natural in %s.
                
                    Generate a complete healing journey based on this placement.
                    Make sure your response has the following structure:
                    {{
                        "mainTitle": "Chiron in Scorpio in the 4th House",
                        "description": "A reflective overview of this placement’s emotional and spiritual themes.",
                
                        "coreWoundsAndEmotionalThemes": ["keywords that capture deep emotional wounds, e.g., abandonment, betrayal, etc."],
                        "patternsAndStruggles": ["keywords that reflect common behavioral or emotional struggles."],
                        "healingAndTransformation": ["keywords that represent the healing path and emotional growth."],
                        "spiritualWisdomAndGifts": ["keywords showing the spiritual gifts and insights gained through healing."],
                
                        "woundPoints": ["Write 3–4 emotional facts or experiences that often come with this Chiron placement."],
                
                        "patternsConnectedToThisWound": ["Describe 3–4 behavioral or relational patterns that are shaped by this wound."],
                
                        "healingBenefits": ["List 3–4 healing outcomes — personal growth, peace, transformation — that come from facing and healing this wound."]
                    }}
                
                    💡 Tone & Style:
                    - Safe and reflective
                    - Poetic, warm, empowering
                    - Gentle and trauma-aware
                    - Emotionally intelligent and culturally appropriate in {language}
                
                    Notes for writing:
                    - For `description`: Provide a brief but emotionally deep overview of the Chiron wound and healing potential.
                    - For keyword sections: Only list **relevant themes as short phrases**.
                    - For bullet-point sections: Create **natural-sounding, insightful sentences** that reflect lived emotional experience.""", r.getBirthDate(), r.getTime(), r.getBirthPlace(), r.getLanguage(), r.getLanguage());
    }

    private String extractTextFromResponse(Map responseBody) {
        try {
            List candidates = (List) responseBody.get("candidates");
            Map firstCandidate = (Map) candidates.get(0);
            Map content = (Map) firstCandidate.get("content");
            List parts = (List) content.get("parts");
            return (String) ((Map) parts.get(0)).get("text");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response", e);
        }
    }

    private String extractJsonString(String fullText) {
        Pattern pattern = Pattern.compile("\\{[\\s\\S]*\\}");
        Matcher matcher = pattern.matcher(fullText);
        if (matcher.find()) return matcher.group(0);
        throw new RuntimeException("No JSON found in response");
    }
}
