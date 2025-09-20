package com.inner_code.service;

import com.inner_code.dto.ReflectiveAnswersDTO;
import com.inner_code.enums.AnswerStatus;
import com.inner_code.model.ReflectiveQuestion;
import com.inner_code.repository.ReflectiveQuestionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class ReflectiveQuestionService {
    private final ReflectiveQuestionRepository reflectiveQuestionRepository;
    @Transactional
    public void saveAnswers(ReflectiveAnswersDTO dto) {
        dto.getAnswers().forEach((questionId, answerValue) -> {
            ReflectiveQuestion rq = reflectiveQuestionRepository.findById(questionId)
                    .orElseThrow(() -> new IllegalArgumentException("Question not found: " + questionId));
            rq.setAnswer(AnswerStatus.valueOf(answerValue).getValue());
            reflectiveQuestionRepository.save(rq);
        });
    }


}
