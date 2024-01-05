package com.emilmi.resume.project;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProjectCategoryMapper.class)
public interface ProjectMapper {
    @Mapping(target = "categoryNames", ignore = true)
    ProjectDto toDto(Project project);

    @Mapping(target = "categories", source = "projectDto.categoryNames", qualifiedBy = CategoryLookup.class)
    Project toDocument(ProjectDto projectDto);

    @Mapping(target = "id", source = "project.id")
    @Mapping(target = "categories", source = "projectDto.categoryNames", qualifiedBy = CategoryLookup.class)
    @Mapping(target = "name", source = "projectDto.name")
    @Mapping(target = "description", source = "projectDto.description")
    @Mapping(target = "url", source = "projectDto.url")
    @Mapping(target = "imageUrl", source = "projectDto.imageUrl")
    @Mapping(target = "createdDateTime", source = "project.createdDateTime")
    @Mapping(target = "modifiedDateTime", ignore = true)
    Project toDocument(ProjectDto projectDto, Project project);
}
