package com.emilmi.resume.skill_group;

import com.emilmi.resume.skill.SkillDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = SkillSkillGroupMapper.class)
public interface SkillGroupMapper {
    @Mapping(target = "skills", ignore = true)
    SkillGroupDto toDto(SkillGroup SkillGroup);

    @Mapping(target = "skills", source = "skillGroup", qualifiedBy = SkillLookup.class)
    SkillGroupDto toDto(SkillGroup skillGroup, List<SkillDto> skills);

    SkillGroup toDocument(SkillGroupDto skillGroupDto);

    @Mapping(target = "id", source = "id")
    SkillGroup toDocument(SkillGroupDto skillGroupDto, String id);
}
