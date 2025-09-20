package com.inner_code.mapper;

import com.inner_code.dto.HealingPlanResponse;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.dto.ReflectiveQuestionDTO;
import com.inner_code.enums.ActivityStatus;
import com.inner_code.enums.SubscriptionPlan;
import com.inner_code.model.CoreWound;
import com.inner_code.model.HealingAndTransformation;
import com.inner_code.model.HealingBenefit;
import com.inner_code.model.HealingPlan;
import com.inner_code.model.PatternAndStruggle;
import com.inner_code.model.PatternConnectedToWound;
import com.inner_code.model.PersonalInfo;
import com.inner_code.model.Prompt;
import com.inner_code.model.ReflectiveQuestion;
import com.inner_code.model.SpiritualWisdomAndGift;
import com.inner_code.model.WoundPoint;

import java.util.ArrayList;
import java.util.List;

import static com.inner_code.enums.ActivityStatus.NOT_STARTED;

public class DtoToInfoMapper {
    public static void mapPersonalInfo(PersonalOverViewDto dto,
                                       PersonalInfo info,
                                       Long userId,
                                       HealingPlanResponse planResponse) {
        info.setUserId(userId);
        info.setSign(dto.getSign());
        info.setHouse(dto.getHouse());
        info.setMainTitle(dto.getMainTitle());
        info.setDescription(dto.getDescription());
        if (info.getReflectiveQuestions() != null &&
                info.getReflectiveQuestions().stream().allMatch(q -> q.getAnswer() != null)) {
            info.setSubscription(SubscriptionPlan.FREE.getValue());
        }
        mapCoreWounds(dto, info);
        mapPatternsAndStruggles(dto, info);
        mapHealingAndTransformations(dto, info);
        mapSpiritualWisdom(dto, info);
        mapWoundPoints(dto, info);
        mapPatternsConnectedToWound(dto, info);
        mapHealingBenefits(dto, info);
        mapHealingPlan(planResponse, info);
        mapReflcetiveQuestions(dto, info);

    }
    private static void mapCoreWounds(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getCoreWoundsAndEmotionalThemes() != null) {
            List<CoreWound> list = new ArrayList<>();
            for (String val : dto.getCoreWoundsAndEmotionalThemes()) {
                CoreWound entity = new CoreWound();
                entity.setValue(val);
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setCoreWoundsAndEmotionalThemes(list);
        }
    }

    private static void mapPatternsAndStruggles(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getPatternsAndStruggles() != null) {
            List<PatternAndStruggle> list = new ArrayList<>();
            for (String val : dto.getPatternsAndStruggles()) {
                PatternAndStruggle entity = new PatternAndStruggle();
                entity.setValue(val);
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setPatternsAndStruggles(list);
        }
    }

    private static void mapHealingAndTransformations(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getHealingAndTransformation() != null) {
            List<HealingAndTransformation> list = new ArrayList<>();
            for (String val : dto.getHealingAndTransformation()) {
                HealingAndTransformation entity = new HealingAndTransformation();
                entity.setValue(val);
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setHealingAndTransformations(list);
        }
    }

    private static void mapSpiritualWisdom(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getSpiritualWisdomAndGifts() != null) {
            List<SpiritualWisdomAndGift> list = new ArrayList<>();
            for (String val : dto.getSpiritualWisdomAndGifts()) {
                SpiritualWisdomAndGift entity = new SpiritualWisdomAndGift();
                entity.setValue(val);
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setSpiritualWisdomAndGifts(list);
        }
    }

    private static void mapWoundPoints(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getWoundPoints() != null) {
            List<WoundPoint> list = new ArrayList<>();
            for (String val : dto.getWoundPoints()) {
                WoundPoint entity = new WoundPoint();
                entity.setValue(val);
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setWoundPoints(list);
        }
    }

    private static void mapPatternsConnectedToWound(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getPatternsConnectedToThisWound() != null) {
            List<PatternConnectedToWound> list = new ArrayList<>();
            for (String val : dto.getPatternsConnectedToThisWound()) {
                PatternConnectedToWound entity = new PatternConnectedToWound();
                entity.setValue(val);
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setPatternsConnectedToThisWound(list);
        }
    }

    private static void mapHealingBenefits(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getHealingBenefits() != null) {
            List<HealingBenefit> list = new ArrayList<>();
            for (String val : dto.getHealingBenefits()) {
                HealingBenefit entity = new HealingBenefit();
                entity.setValue(val);
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setHealingBenefits(list);
        }
    }
    private static void mapReflcetiveQuestions(PersonalOverViewDto dto, PersonalInfo info) {
        if (dto.getHealingBenefits() != null) {
            List<ReflectiveQuestion> list = new ArrayList<>();
            for (ReflectiveQuestionDTO val : dto.getReflectiveQuestions()) {
                ReflectiveQuestion entity = new ReflectiveQuestion();
                entity.setQuestion(val.getQuestion());
                entity.setAnswer(NOT_STARTED.getCode());
                entity.setPersonalInfo(info);
                list.add(entity);
            }
            info.setReflectiveQuestions(list);
        }
    }
    private static void mapHealingPlan(HealingPlanResponse planResponse, PersonalInfo info) {
        if (planResponse != null && planResponse.getPlan() != null && !planResponse.getPlan().isEmpty()) {

            List<HealingPlan> plans = new ArrayList<>();

            for (HealingPlanResponse.PlanItem planItem : planResponse.getPlan()) {
                HealingPlan healingPlan = new HealingPlan();
                healingPlan.setOverview(planItem.getOverview());
                healingPlan.setActivity(planItem.getActivity());
                healingPlan.setMeditation(planItem.getMeditation());
                healingPlan.setStatus(NOT_STARTED.toString());
                healingPlan.setPersonalInfo(info);

                if (planItem.getPrompts() != null) {
                    List<Prompt> prompts = new ArrayList<>();
                    for (String promptText : planItem.getPrompts()) {
                        Prompt prompt = new Prompt();
                        prompt.setQuestion(promptText);
                        prompt.setStatus(NOT_STARTED.toString());
                        prompt.setHealingPlan(healingPlan);
                        prompts.add(prompt);
                    }
                    healingPlan.setPrompts(prompts);
                }

                plans.add(healingPlan);
            }
            info.setHealingPlans(plans);
        }
    }
}
