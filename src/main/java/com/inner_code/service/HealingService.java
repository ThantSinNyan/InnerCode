package com.inner_code.service;

import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.feignClient.HealingFeignClient;
import org.springframework.stereotype.Service;

@Service
public class HealingService {

    private final HealingFeignClient healingFeignClient;

    public HealingService(HealingFeignClient healingFeignClient) {
        this.healingFeignClient = healingFeignClient;
    }
    public PersonalOverViewDto getHealingOverview(String birthDate, String birthTime, String birthPlace, String language) {
        HealingRequest request = new HealingRequest(birthDate, birthTime, birthPlace, language);
        PersonalOverViewDto personalOverViewDto=healingFeignClient.generateOverview(request);
        personalOverViewDto.setMainTitle("Chiron in "+personalOverViewDto.getSign()+" in the "+personalOverViewDto.getHouse()+" house");
        return personalOverViewDto;
    }
}