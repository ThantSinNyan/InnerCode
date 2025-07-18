package com.inner_code.dto;

import lombok.Data;

import java.util.List;
@Data
public class PersonalOverViewDto {
    private String mainTitle;
    private String description;
    private List<String> coreWoundsAndEmotionalThemes;
    private List<String> patternsAndStruggles;
    private List<String> healingAndTransformation;
    private List<String> spiritualWisdomAndGifts;
    private List<String> woundPoints;
    private List<String> patternsConnectedToThisWound;
    private List<String> healingBenefits;

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCoreWoundsAndEmotionalThemes() {
        return coreWoundsAndEmotionalThemes;
    }

    public void setCoreWoundsAndEmotionalThemes(List<String> coreWoundsAndEmotionalThemes) {
        this.coreWoundsAndEmotionalThemes = coreWoundsAndEmotionalThemes;
    }

    public List<String> getPatternsAndStruggles() {
        return patternsAndStruggles;
    }

    public void setPatternsAndStruggles(List<String> patternsAndStruggles) {
        this.patternsAndStruggles = patternsAndStruggles;
    }

    public List<String> getHealingAndTransformation() {
        return healingAndTransformation;
    }

    public void setHealingAndTransformation(List<String> healingAndTransformation) {
        this.healingAndTransformation = healingAndTransformation;
    }

    public List<String> getSpiritualWisdomAndGifts() {
        return spiritualWisdomAndGifts;
    }

    public void setSpiritualWisdomAndGifts(List<String> spiritualWisdomAndGifts) {
        this.spiritualWisdomAndGifts = spiritualWisdomAndGifts;
    }

    public List<String> getWoundPoints() {
        return woundPoints;
    }

    public void setWoundPoints(List<String> woundPoints) {
        this.woundPoints = woundPoints;
    }

    public List<String> getPatternsConnectedToThisWound() {
        return patternsConnectedToThisWound;
    }

    public void setPatternsConnectedToThisWound(List<String> patternsConnectedToThisWound) {
        this.patternsConnectedToThisWound = patternsConnectedToThisWound;
    }

    public List<String> getHealingBenefits() {
        return healingBenefits;
    }

    public void setHealingBenefits(List<String> healingBenefits) {
        this.healingBenefits = healingBenefits;
    }
}
