package com.inner_code.service;

import com.inner_code.dto.HealingPlanRequest;
import com.inner_code.dto.HealingPlanResponse;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.feignClient.HealingFeignClient;
import com.inner_code.model.*;
import com.inner_code.repository.PersonalInfoRepository;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.inner_code.mapper.DtoToInfoMapper.mapPersonalInfo;
import static com.inner_code.mapper.PersonalInfoMapper.mapToDto;
import static com.inner_code.util.Constants.healingPlanGuideQuestion;

@Service
@AllArgsConstructor
@ToString
public class HealingOverviewService {

    private final PersonalInfoRepository personalInfoRepository;
    private final HealingFeignClient healingFeignClient;

    public List<PersonalOverViewDto> getPersonalInsideDataOverviewByUserId(HealingRequest healingRequest) {
        Long userId = Long.valueOf(healingRequest.getUserId());
        List<PersonalInfo> infos = personalInfoRepository.findByUserId(userId);
        List<PersonalOverViewDto> personalOverViewDtoList=new ArrayList<>();
        for(PersonalInfo info:infos){
            PersonalOverViewDto personalOverViewDto=mapToDto(info);
            personalOverViewDto.setHealingPlans(info.getHealingPlans());
            personalOverViewDtoList.add(personalOverViewDto);
        }
        return personalOverViewDtoList;
    }

    public PersonalOverViewDto getPersonalInsideDataOverviewById(HealingRequest healingRequest) {
        Long id = Long.valueOf(healingRequest.getId());
        PersonalInfo info = personalInfoRepository.findById(id).orElse(null);
        PersonalOverViewDto personalOverViewDto=mapToDto(info);
        personalOverViewDto.setHealingPlans(info.getHealingPlans());
        return personalOverViewDto;
    }

    public PersonalOverViewDto generateAndSaveHealingOverview(HealingRequest healingRequest) {
        Long userId = Long.valueOf(healingRequest.getUserId());
        healingRequest.setLanguage("english");
        return generateAndSaveNewOverview(healingRequest, userId);
    }

    private PersonalOverViewDto generateAndSaveNewOverview(HealingRequest healingRequest, Long userId) {
        PersonalOverViewDto overviewDto = healingFeignClient.generateOverview(healingRequest);
        overviewDto.setMainTitle(buildMainTitle(overviewDto));
        System.out.println("overviewDto.getReflectiveQuestion-->"+overviewDto.getReflectiveQuestions());
        HealingPlanResponse planResponse = generateHealingPlan(overviewDto);

        PersonalInfo info=savePersonalOverview(overviewDto, userId, planResponse);

        PersonalOverViewDto personalOverViewDto=mapToDto(info);
        personalOverViewDto.setId(info.getId()+"");
        personalOverViewDto.setHealingPlans(info.getHealingPlans());
        return personalOverViewDto;
    }
    private PersonalInfo savePersonalOverview(PersonalOverViewDto dto, Long userId, HealingPlanResponse planResponse) {
        PersonalInfo info = new PersonalInfo();
        mapPersonalInfo(dto, info,userId,planResponse);
        return personalInfoRepository.save(info);
    }
    private HealingPlanResponse generateHealingPlan(PersonalOverViewDto overviewDto) {
        HealingPlanRequest planRequest = new HealingPlanRequest();
        planRequest.setSign(overviewDto.getSign());
        planRequest.setHouse(overviewDto.getHouse());
        planRequest.setQuestion(healingPlanGuideQuestion);
        planRequest.setLanguage("english");
        return healingFeignClient.generatePlan(planRequest);
    }
    private String buildMainTitle(PersonalOverViewDto overviewDto) {
        return "Chiron in " + overviewDto.getSign() + " in the " + overviewDto.getHouse() + " house";
    }

}
