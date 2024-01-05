package com.emilmi.resume.work_experience;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record WorkExperienceDto(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String id,
        @NotEmpty(message = "name cannot be empty")
        String name,
        @NotEmpty(message = "description cannot be empty")
        String description,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDate startDate,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDate endDate,
        @NotEmpty(message = "projects cannot be empty")
        @Valid List<ProjectDto> projects
) {
}
