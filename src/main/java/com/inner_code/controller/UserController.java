package com.inner_code.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inner_code.dto.HealingRequest;
import com.inner_code.dto.PersonalOverViewDto;
import com.inner_code.model.User;
import com.inner_code.service.HealingService;
import com.inner_code.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final HealingService healingService;
    @GetMapping("/search")
    public User getUsers() {
        return userService.getUser();
    }

    @PostMapping("/personal-inside-data-overview")
    public ResponseEntity<PersonalOverViewDto> getPersonalInsideDataOverview(@RequestBody HealingRequest request) throws JsonProcessingException {
        System.out.println("request--> "+request);
        try {
            Thread.sleep(50_00); // Wait for 10 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt flag
            throw new RuntimeException("Interrupted while waiting", e);
        }

        PersonalOverViewDto response = healingService.getHealingData(request);
        return ResponseEntity.ok(response);
//        return ResponseEntity.ok(getPersonalOverView());
    }
//    private PersonalOverViewDto getPersonalOverView(){
//        PersonalOverViewDto response = new PersonalOverViewDto();
//
//        response.setMainTitle("Chiron in Scorpio in the 4th House");
//        response.setDescription("With Chiron residing in Scorpio within the 4th House, a deep well of emotional intensity resides at the heart of your home and family..."
//                + " Healing this wound involves a courageous journey into the shadowy depths of your personal history, ultimately leading to a powerful reclamation of your inner authority and a profound sense of self-acceptance.");
//
//        response.setCoreWoundsAndEmotionalThemes(List.of(
//                "Deeply buried family secrets",
//                "Powerlessness and control issues",
//                "Intense emotional volatility",
//                "Trauma related to home and family"
//        ));
//
//        response.setPatternsAndStruggles(List.of(
//                "Difficulty establishing healthy boundaries",
//                "Intense emotional reactions",
//                "Repressed emotions",
//                "Fear of vulnerability"
//        ));
//
//        response.setHealingAndTransformation(List.of(
//                "Embracing emotional vulnerability",
//                "Cultivating self-compassion",
//                "Developing healthy emotional regulation",
//                "Setting firm boundaries",
//                "Forgiveness, both of self and others"
//        ));
//
//        response.setSpiritualWisdomAndGifts(List.of(
//                "Intuition and psychic sensitivity",
//                "Deep empathy and understanding",
//                "Transformation through embracing the shadow",
//                "Wise healer of others’ emotional wounds"
//        ));
//
//        response.setWoundPoints(List.of(
//                "You may have experienced a childhood marked by emotional intensity...",
//                "There might have been power imbalances within your family...",
//                "You may have learned to repress your emotions...",
//                "The concept of ‘home’ and ‘family’ may hold a complex and potentially painful significance..."
//        ));
//
//        response.setPatternsConnectedToThisWound(List.of(
//                "You may find yourself drawn to intense, dramatic relationships...",
//                "You might struggle with setting healthy boundaries...",
//                "Repressed emotions might surface unexpectedly...",
//                "You may unconsciously seek to recreate familiar family dynamics..."
//        ));
//
//        response.setHealingBenefits(List.of(
//                "By confronting these deeply held wounds, you can cultivate a profound sense of self-compassion...",
//                "You will develop the ability to set strong, healthy boundaries...",
//                "Healing this Chironic wound allows you to transform the intensity of your emotions...",
//                "You can emerge from this journey with a deep understanding of your own power..."
//        ));
//     return response;
//    }

}
