package com.inner_code.controller;

import com.inner_code.dto.UserDto;
import com.inner_code.model.User;
import com.inner_code.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }
    @GetMapping("/home")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("UserDto", new UserDto());
        return "user-register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("UserDto") UserDto dto,
                             BindingResult result) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto", "confirmPassword", "Passwords do not match"));
        }

        if (repo.existsByEmail(dto.getEmail())) {
            result.addError(new FieldError("registerDto", "email", "Email already in use"));
        }

        if (result.hasErrors()) {
            return "user-register";
        }

        User ua = new User();
        ua.setName(dto.getName());
        ua.setEmail(dto.getEmail());
        ua.setPasswordHash(encoder.encode(dto.getPassword()));
        ua.setRole("ROLE_USER");

        repo.save(ua);
        return "redirect:/home";
    }
}