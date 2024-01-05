package com.emilmi.resume.category;

import jakarta.validation.constraints.NotEmpty;

public record CategoryDto(
        String id,
        @NotEmpty(message = "name cannot be empty")
        String name
) {
}
