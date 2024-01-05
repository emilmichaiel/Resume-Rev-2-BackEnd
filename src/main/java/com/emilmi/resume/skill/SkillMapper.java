package com.emilmi.resume.skill;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SkillGroupSkillMapper.class)
public interface SkillMapper {
    @Mapping(target = "skillGroupId", ignore = true)
    SkillDto toDto(Skill skill);

    @Mapping(target = "skillGroupId", ignore = true)
    @Mapping(target = "skillGroup", ignore = true)
    SkillDto toDtoNoSkillGroup(Skill skill);

    @Mapping(target = "skillGroup", source = "skillDto.skillGroupId", qualifiedBy = SkillGroupLookup.class)
    Skill toDocument(SkillDto skillDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "skillGroup", source = "skillDto.skillGroupId", qualifiedBy = SkillGroupLookup.class)
    Skill toDocument(SkillDto skillDto, String id);
}
