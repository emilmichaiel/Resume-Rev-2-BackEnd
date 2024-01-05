package com.emilmi.resume.skill_group;

import com.emilmi.resume.skill.SkillDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SkillGroupDto(
        String id,
        @NotEmpty(message = "name cannot be empty")
        String name,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<SkillDto> skills
) {
}
