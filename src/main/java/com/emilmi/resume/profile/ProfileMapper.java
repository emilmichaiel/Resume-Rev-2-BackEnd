package com.emilmi.resume.profile;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto toDto(Profile profile);

    @Mapping(target = "id", ignore = true)
    Profile toDocument(ProfileDto profileDto);
}
