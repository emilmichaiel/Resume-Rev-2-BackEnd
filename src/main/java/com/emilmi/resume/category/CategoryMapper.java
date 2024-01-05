package com.emilmi.resume.category;

import com.emilmi.resume.common.ToLowerCaseAndTrimTransformation;
import com.emilmi.resume.common.ToLowerCaseAndTrimTransformationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ToLowerCaseAndTrimTransformationMapper.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    @Mapping(target = "name", source = "categoryDto.name", qualifiedBy = ToLowerCaseAndTrimTransformation.class)
    Category toDocument(CategoryDto categoryDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "categoryDto.name", qualifiedBy = ToLowerCaseAndTrimTransformation.class)
    Category toDocument(CategoryDto categoryDto, String id);
}
