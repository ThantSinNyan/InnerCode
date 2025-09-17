package com.inner_code.service;

import com.inner_code.dto.HealingPlanRequest;
import com.inner_code.dto.HealingPlanResponse;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.enums.ActivityStatus;
import com.inner_code.feignClient.HealingFeignClient;
import com.inner_code.model.*;
import com.inner_code.repository.PersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.inner_code.mapper.PersonalInfoMapper.mapToDto;
import static com.inner_code.util.Constants.healingPlanGuideQuestion;

@Service
public class HealingService {

    private final PersonalInfoRepository personalInfoRepository;
    private final HealingFeignClient healingFeignClient;

    public HealingService(PersonalInfoRepository personalInfoRepository,
                          HealingFeignClient healingFeignClient) {
        this.personalInfoRepository = personalInfoRepository;
        this.healingFeignClient = healingFeignClient;
    }

    public PersonalOverViewDto getHealingOverview(HealingRequest healingRequest) {
        Long userId = Long.valueOf(healingRequest.getUserId());

        List<PersonalInfo> infos = personalInfoRepository.findByUserId(userId);
        if (infos != null && !infos.isEmpty()) {
            return mapToDto(infos.get(0));
        }

        return generateAndSaveNewOverview(healingRequest, userId);
    }

    private PersonalOverViewDto generateAndSaveNewOverview(HealingRequest healingRequest, Long userId) {
        PersonalOverViewDto overviewDto = healingFeignClient.generateOverview(healingRequest);
        overviewDto.setMainTitle(buildMainTitle(overviewDto));
        HealingPlanResponse planResponse = generateHealingPlan(overviewDto);
        savePersonalOverview(overviewDto, userId, planResponse);
        return overviewDto;
    }

    private String buildMainTitle(PersonalOverViewDto overviewDto) {
        return "Chiron in " + overviewDto.getSign() + " in the " + overviewDto.getHouse() + " house";
    }
    private HealingPlanResponse generateHealingPlan(PersonalOverViewDto overviewDto) {
        HealingPlanRequest planRequest = new HealingPlanRequest();
        planRequest.setSign(overviewDto.getSign());
        planRequest.setHouse(overviewDto.getHouse());
        planRequest.setQuestion(healingPlanGuideQuestion);

        return healingFeignClient.generatePlan(planRequest);
    }

    private void savePersonalOverview(PersonalOverViewDto dto, Long userId, HealingPlanResponse planResponse) {
        PersonalInfo info = new PersonalInfo();
        info.setUserId(userId);
        info.setSign(dto.getSign());
        info.setHouse(dto.getHouse());
        info.setMainTitle(dto.getMainTitle());
        info.setDescription(dto.getDescription());

        // Map sections
        mapCoreWounds(dto, info);
        mapPatternsAndStruggles(dto, info);
        mapHealingAndTransformations(dto, info);
        mapSpiritualWisdom(dto, info);
        mapWoundPoints(dto, info);
        mapPatternsConnectedToWound(dto, info);
        mapHealingBenefits(dto, info);

        // Map Healing Plan
        mapHealingPlan(planResponse, info);

        personalInfoRepository.save(info);
    }

    private void mapCoreWounds(PersonalOverViewDto dto, PersonalInfo info) {
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

    private void mapPatternsAndStruggles(PersonalOverViewDto dto, PersonalInfo info) {
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

    private void mapHealingAndTransformations(PersonalOverViewDto dto, PersonalInfo info) {
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

    private void mapSpiritualWisdom(PersonalOverViewDto dto, PersonalInfo info) {
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

    private void mapWoundPoints(PersonalOverViewDto dto, PersonalInfo info) {
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

    private void mapPatternsConnectedToWound(PersonalOverViewDto dto, PersonalInfo info) {
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

    private void mapHealingBenefits(PersonalOverViewDto dto, PersonalInfo info) {
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
    private void mapHealingPlan(HealingPlanResponse planResponse, PersonalInfo info) {
        if (planResponse != null && planResponse.getPlan() != null && !planResponse.getPlan().isEmpty()) {
            HealingPlanResponse.PlanItem planItem = planResponse.getPlan().get(0);

            HealingPlan healingPlan = new HealingPlan();
            healingPlan.setOverview(planItem.getOverview());
            healingPlan.setActivity(planItem.getActivity());
            healingPlan.setMeditation(planItem.getMeditation());
            healingPlan.setStatus(ActivityStatus.NOT_STARTED.toString());
            healingPlan.setPersonalInfo(info);

            if (planItem.getPrompts() != null) {
                List<Prompt> prompts = new ArrayList<>();
                for (String promptText : planItem.getPrompts()) {
                    Prompt prompt = new Prompt();
                    prompt.setQuestion(promptText);
                    prompt.setStatus(ActivityStatus.NOT_STARTED.toString());
                    prompt.setHealingPlan(healingPlan);
                    prompts.add(prompt);
                }
                healingPlan.setPrompts(prompts);
            }

            info.setHealingPlan(healingPlan);
        }
    }
}
