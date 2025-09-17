package com.inner_code.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    @NotBlank(message = "Name required")
    private String name;

    @Email @NotBlank(message = "Email required")
    private String email;

    @NotBlank(message = "Password required")
    private String password;

    @NotBlank(message = "Confirm your password")
    private String confirmPassword;
}