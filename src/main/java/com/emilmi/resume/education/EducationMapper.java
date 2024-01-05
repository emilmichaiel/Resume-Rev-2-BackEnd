package com.emilmi.resume.education;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    EducationDto toDto(Education education);

    Education toDocument(EducationDto educationDto);

    @Mapping(target = "id", source = "id")
    Education toDocument(EducationDto educationDto, String id);
}
