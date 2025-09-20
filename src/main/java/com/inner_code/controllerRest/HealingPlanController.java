package com.inner_code.controllerRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.service.HealingOverviewService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@AllArgsConstructor
public class HealingPlanController {
    private final HealingOverviewService healingService;

    @PostMapping("/generate-personal-inside-data-overview")
    public ResponseEntity<PersonalOverViewDto> generatePersonalInsideDataOverview(@RequestBody HealingRequest request,
                                                                             @AuthenticationPrincipal Jwt principal) throws JsonProcessingException {
        PersonalOverViewDto response = healingService.generateAndSaveHealingOverview(
                request
        );
        return ResponseEntity.ok(response);
    }
    @PostMapping("/getHealingJourneyByUserId")
    public ResponseEntity<List<PersonalOverViewDto>> getPersonalInsideDataOverviewByUserId(@RequestBody HealingRequest request,
                                                                                   @AuthenticationPrincipal Jwt principal) throws JsonProcessingException {
        List<PersonalOverViewDto> response = healingService.getPersonalInsideDataOverviewByUserId(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/get-personal-inside-data-overview")
    public ResponseEntity<PersonalOverViewDto> getPersonalInsideDataOverviewById(@RequestBody HealingRequest request,
                                                                             @AuthenticationPrincipal Jwt principal) throws JsonProcessingException {
        PersonalOverViewDto response = healingService.getPersonalInsideDataOverviewById(request);
        return ResponseEntity.ok(response);
    }

}
