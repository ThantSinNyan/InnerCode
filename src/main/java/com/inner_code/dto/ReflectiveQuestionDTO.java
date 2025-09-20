package com.inner_code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ReflectiveQuestionDTO {
    private Long id;
    private String question;
    private Integer answer;
}
