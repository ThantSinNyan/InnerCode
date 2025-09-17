package com.inner_code.controllerRest;

import com.inner_code.dto.LoginResponseDto;
import com.inner_code.dto.UserDto;
import com.inner_code.model.User;
import com.inner_code.repository.UserRepository;
import com.inner_code.securityConfig.JwtUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private ModelMapper modelMapper;
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public AuthRestController(UserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto dto, BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            errors.put("confirmPassword", "Passwords do not match");
        }

        if (repo.existsByEmail(dto.getEmail())) {
            errors.put("email", "Email already in use");
        }

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError ->
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(encoder.encode(dto.getPassword()));
        user.setRole("ROLE_USER");

        repo.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", user.getId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto dto) {
        User user = repo.findByEmail(dto.getEmail()).orElse(null);

        if (user == null || !encoder.matches(dto.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }

        // ✅ Generate JWT token (valid for 10 minutes)
        String token = JwtUtil.generateToken(user.getEmail(), user.getRole());

        LoginResponseDto response = modelMapper.map(user, LoginResponseDto.class);
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/home")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok(Map.of("message", "Welcome to the home page"));
    }
}
