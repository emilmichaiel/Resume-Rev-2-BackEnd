package com.emilmi.resume.education;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EducationDto(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String id,
        @NotEmpty(message = "name cannot be empty")
        String name,
        @NotEmpty(message = "description cannot be empty")
        String description,
        @NotNull(message = "startDate cannot be empty")
        LocalDate startDate,
        @NotNull(message = "endDate cannot be empty")
        LocalDate endDate
) {
}
