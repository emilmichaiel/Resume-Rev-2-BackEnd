package com.emilmi.resume.security;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(
        @NotEmpty(message = "email cannot be empty")
        String email,
        @NotEmpty(message = "password cannot be empty")
        String password
) {
}
