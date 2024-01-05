package com.emilmi.resume.project;

import com.emilmi.resume.category.Category;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectDto(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String id,
        String name,
        String description,
        String url,
        String imageUrl,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<Category> categories,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<String> categoryNames,
        LocalDateTime createdDateTime,
        LocalDateTime modifiedDateTime
) {
}
