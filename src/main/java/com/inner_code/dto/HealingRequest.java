package com.inner_code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HealingRequest {
    private String id;
    private String userId;
    private String birthDate;
    private String birthTime;
    private String birthPlace;
    private String language;
}

