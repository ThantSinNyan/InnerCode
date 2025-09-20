package com.inner_code.mapper;

import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.dto.ReflectiveQuestionDTO;
import com.inner_code.enums.SubscriptionPlan;
import com.inner_code.model.PersonalInfo;
import com.inner_code.model.*;

public class PersonalInfoMapper {
    public static PersonalOverViewDto mapToDto(PersonalInfo info) {
        PersonalOverViewDto dto = new PersonalOverViewDto();
        dto.setId(info.getId()+"");
        dto.setSign(info.getSign());
        dto.setHouse(info.getHouse());
        dto.setMainTitle(info.getMainTitle());
        dto.setDescription(info.getDescription());
        dto.setSubscription(SubscriptionPlan.fromValue(info.getSubscription()).name());

        dto.setCoreWoundsAndEmotionalThemes(
                info.getCoreWoundsAndEmotionalThemes().stream()
                        .map(CoreWound::getValue)
                        .toList()
        );
        dto.setPatternsAndStruggles(
                info.getPatternsAndStruggles().stream()
                        .map(PatternAndStruggle::getValue)
                        .toList()
        );
        dto.setHealingAndTransformation(
                info.getHealingAndTransformations().stream()
                        .map(HealingAndTransformation::getValue)
                        .toList()
        );
        dto.setSpiritualWisdomAndGifts(
                info.getSpiritualWisdomAndGifts().stream()
                        .map(SpiritualWisdomAndGift::getValue)
                        .toList()
        );
        dto.setWoundPoints(
                info.getWoundPoints().stream()
                        .map(WoundPoint::getValue)
                        .toList()
        );
        dto.setPatternsConnectedToThisWound(
                info.getPatternsConnectedToThisWound().stream()
                        .map(PatternConnectedToWound::getValue)
                        .toList()
        );
        dto.setHealingBenefits(
                info.getHealingBenefits().stream()
                        .map(HealingBenefit::getValue)
                        .toList()
        );
        dto.setReflectiveQuestionDTOs(
                info.getReflectiveQuestions().stream()
                        .map(rq -> new ReflectiveQuestionDTO(
                                rq.getId(),
                                rq.getQuestion(),
                                rq.getAnswer()
                        ))
                        .toList()
        );
        return dto;
    }

}
