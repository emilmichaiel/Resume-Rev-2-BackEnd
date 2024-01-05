package com.emilmi.resume.security;

import jakarta.validation.constraints.NotEmpty;

public record AuthDto(
        String token,
        long tokenExpiration,
        @NotEmpty(message = "refreshToken cannot be empty")
        String refreshToken,
        long refreshExpiration
) {
}
