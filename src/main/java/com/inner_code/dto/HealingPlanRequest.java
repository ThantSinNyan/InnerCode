package com.inner_code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealingPlanRequest {
    private String sign;
    private String house;
    private String question;
}