package com.inner_code.dto;

import java.util.Map;

public class ReflectiveAnswersDTO {
    private Map<Long, String> answers;
    public Map<Long, String> getAnswers() {
        return answers;
    }
    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }
}
