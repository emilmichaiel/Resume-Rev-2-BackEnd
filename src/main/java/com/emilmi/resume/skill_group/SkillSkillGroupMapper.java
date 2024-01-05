package com.emilmi.resume.skill_group;

import com.emilmi.resume.skill.SkillDto;
import com.emilmi.resume.skill.SkillRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillSkillGroupMapper {
    private final SkillRepository skillRepository;

    public SkillSkillGroupMapper(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @SkillLookup
    public List<SkillDto> map(SkillGroup skillGroup) {
        return skillRepository.findBySkillGroup(skillGroup)
                .stream().map(skill ->
                        new SkillDto(
                                skill.getId(),
                                skill.getName(),
                                skill.getLevel(),
                                skill.getExtraNotes(),
                                null,
                                null
                        )
                ).toList();
    }
}
