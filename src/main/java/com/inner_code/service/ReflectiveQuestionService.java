package com.inner_code.service;

import com.inner_code.dto.ReflectiveAnswersDTO;
import com.inner_code.enums.AnswerStatus;
import com.inner_code.enums.SubscriptionPlan;
import com.inner_code.model.PersonalInfo;
import com.inner_code.model.ReflectiveQuestion;
import com.inner_code.repository.PersonalInfoRepository;
import com.inner_code.repository.ReflectiveQuestionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReflectiveQuestionService {
    private final PersonalInfoRepository personalInfoService;
    private final ReflectiveQuestionRepository reflectiveQuestionRepository;
    @Transactional
    public void saveAnswers(ReflectiveAnswersDTO dto) {
        List<ReflectiveQuestion> updatedQuestions = new ArrayList<>();

        dto.getAnswers().forEach((questionId, answerValue) -> {
            ReflectiveQuestion rq = reflectiveQuestionRepository.findById(questionId)
                    .orElseThrow(() -> new IllegalArgumentException("Question not found: " + questionId));
            rq.setAnswer(AnswerStatus.valueOf(answerValue).getValue());
            updatedQuestions.add(rq);
        });
        ReflectiveQuestion rq=updatedQuestions.getFirst();
        Optional<PersonalInfo> personalInfo=personalInfoService.findById(rq.getPersonalInfo().getId());
        if(personalInfo.isPresent()){
            System.out.println("Arrive suscription upd Personal Info: "+personalInfo.get());
            personalInfo.get().setSubscription(SubscriptionPlan.FREE.getValue());
            personalInfoService.save(personalInfo.get());
        }
    }


}
