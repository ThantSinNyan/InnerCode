package com.inner_code.controllerRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.service.HealingService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final HealingService healingService;

    public UserController(HealingService healingService) {
        this.healingService = healingService;
    }

    @PostMapping("/personal-inside-data-overview")
    public ResponseEntity<PersonalOverViewDto> getPersonalInsideDataOverview(@RequestBody HealingRequest request,
                                                                             @AuthenticationPrincipal Jwt principal) throws JsonProcessingException {
        PersonalOverViewDto response = healingService.getHealingOverview(
                request
        );
        return ResponseEntity.ok(response);
    }
}
