package com.emilmi.resume.work_experience;

import jakarta.validation.constraints.NotEmpty;

public record ProjectDto(
        @NotEmpty(message = "project name cannot be empty")
        String name,
        @NotEmpty(message = "project description cannot be empty")
        String description,
        String url
) {
}
