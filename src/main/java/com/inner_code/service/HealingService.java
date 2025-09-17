package com.inner_code.service;

import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.feignClient.HealingFeignClient;
import com.inner_code.model.*;
import com.inner_code.repository.PersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.inner_code.mapper.PersonalInfoMapper.mapToDto;

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
        List<PersonalInfo> infos = personalInfoRepository.findByUserId(Long.valueOf(healingRequest.getUserId()));
        if (!infos.isEmpty()) {
            return mapToDto(infos.get(0)); // or handle multiple properly
        }

        PersonalOverViewDto personalOverViewDto = healingFeignClient.generateOverview(healingRequest);
        personalOverViewDto.setMainTitle(
                "Chiron in " + personalOverViewDto.getSign() +
                        " in the " + personalOverViewDto.getHouse() + " house"
        );

        savePersonalOverview(personalOverViewDto, Long.valueOf(healingRequest.getUserId()));

        return personalOverViewDto;
    }

    private void savePersonalOverview(PersonalOverViewDto dto, Long userId) {
        PersonalInfo info = new PersonalInfo();
        info.setUserId(userId);
        info.setSign(dto.getSign());
        info.setHouse(dto.getHouse());
        info.setMainTitle(dto.getMainTitle());
        info.setDescription(dto.getDescription());

        if (dto.getCoreWoundsAndEmotionalThemes() != null) {
            info.setCoreWoundsAndEmotionalThemes(dto.getCoreWoundsAndEmotionalThemes()
                    .stream()
                    .map(val -> {
                        CoreWound entity = new CoreWound();
                        entity.setValue(val);
                        entity.setPersonalInfo(info);
                        return entity;
                    })
                    .toList());
        }

        if (dto.getPatternsAndStruggles() != null) {
            info.setPatternsAndStruggles(dto.getPatternsAndStruggles()
                    .stream()
                    .map(val -> {
                        PatternAndStruggle entity = new PatternAndStruggle();
                        entity.setValue(val);
                        entity.setPersonalInfo(info);
                        return entity;
                    })
                    .toList());
        }

        if (dto.getHealingAndTransformation() != null) {
            info.setHealingAndTransformations(dto.getHealingAndTransformation()
                    .stream()
                    .map(val -> {
                        HealingAndTransformation entity = new HealingAndTransformation();
                        entity.setValue(val);
                        entity.setPersonalInfo(info);
                        return entity;
                    })
                    .toList());
        }

        if (dto.getSpiritualWisdomAndGifts() != null) {
            info.setSpiritualWisdomAndGifts(dto.getSpiritualWisdomAndGifts()
                    .stream()
                    .map(val -> {
                        SpiritualWisdomAndGift entity = new SpiritualWisdomAndGift();
                        entity.setValue(val);
                        entity.setPersonalInfo(info);
                        return entity;
                    })
                    .toList());
        }

        if (dto.getWoundPoints() != null) {
            info.setWoundPoints(dto.getWoundPoints()
                    .stream()
                    .map(val -> {
                        WoundPoint entity = new WoundPoint();
                        entity.setValue(val);
                        entity.setPersonalInfo(info);
                        return entity;
                    })
                    .toList());
        }

        if (dto.getPatternsConnectedToThisWound() != null) {
            info.setPatternsConnectedToThisWound(dto.getPatternsConnectedToThisWound()
                    .stream()
                    .map(val -> {
                        PatternConnectedToWound entity = new PatternConnectedToWound();
                        entity.setValue(val);
                        entity.setPersonalInfo(info);
                        return entity;
                    })
                    .toList());
        }

        if (dto.getHealingBenefits() != null) {
            info.setHealingBenefits(dto.getHealingBenefits()
                    .stream()
                    .map(val -> {
                        HealingBenefit entity = new HealingBenefit();
                        entity.setValue(val);
                        entity.setPersonalInfo(info);
                        return entity;
                    })
                    .toList());
        }

        personalInfoRepository.save(info);
    }
}
