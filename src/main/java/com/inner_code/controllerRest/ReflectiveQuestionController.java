package com.inner_code.controllerRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.dto.ReflectiveAnswersDTO;
import com.inner_code.service.HealingOverviewService;
import com.inner_code.service.ReflectiveQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@AllArgsConstructor
public class ReflectiveQuestionController {
    private final ReflectiveQuestionService reflectiveQuestionService;
    @PostMapping("/reflective-answers")
    public ResponseEntity<String> saveAnswers(@RequestBody ReflectiveAnswersDTO dto) {
        reflectiveQuestionService.saveAnswers(dto);
        return ResponseEntity.ok("Answers saved successfully!");
    }
}
