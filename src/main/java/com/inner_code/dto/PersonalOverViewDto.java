package com.inner_code.dto;

import lombok.Data;

import java.util.List;
@Data
public class PersonalOverViewDto {
    private String sign;
    private String house;
    private String mainTitle;
    private String description;
    private List<String> coreWoundsAndEmotionalThemes;
    private List<String> patternsAndStruggles;
    private List<String> healingAndTransformation;
    private List<String> spiritualWisdomAndGifts;
    private List<String> woundPoints;
    private List<String> patternsConnectedToThisWound;
    private List<String> healingBenefits;
}
