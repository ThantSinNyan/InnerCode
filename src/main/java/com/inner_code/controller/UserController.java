package com.inner_code.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inner_code.dto.HealingRequest;
import com.inner_code.model.User;
import com.inner_code.service.HealingService;
import com.inner_code.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final HealingService healingService;
    @GetMapping("/search")
    public User getUsers() {
        return userService.getUser();
    }

    @PostMapping("/personal-inside-data-overview")
    public ResponseEntity<Map<String, Object>> getPersonalInsideDataOverview(@RequestBody HealingRequest request) throws JsonProcessingException {
        Map<String, Object> response = healingService.getHealingData(request);
        return ResponseEntity.ok(response);
    }

}
