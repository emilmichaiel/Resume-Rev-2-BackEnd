package com.emilmi.resume.skill;

import com.emilmi.resume.skill_group.SkillGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SkillDto(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String id,
        @NotEmpty(message = "name cannot be empty")
        String name,
        @NotNull(message = "level cannot be empty")
        @DecimalMin(value = "1.0", message = "level can only be between 1 to 10")
        @DecimalMax(value = "10.0", message = "level can only be between 1 to 10")
        Double level,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String extraNotes,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        SkillGroup skillGroup,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String skillGroupId
) {
}
