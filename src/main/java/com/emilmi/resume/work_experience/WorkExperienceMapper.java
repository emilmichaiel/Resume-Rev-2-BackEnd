package com.emilmi.resume.work_experience;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkExperienceMapper {
    WorkExperienceDto toDto(WorkExperience workExperience);

    WorkExperience toDocument(WorkExperienceDto workExperienceDto);

    @Mapping(target = "id", source = "id")
    WorkExperience toDocument(WorkExperienceDto workExperienceDto, String id);
}
