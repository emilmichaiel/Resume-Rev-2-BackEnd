package com.emilmi.resume.category;

import com.emilmi.resume.common.EmilMiGenericService;

public interface CategoryService extends EmilMiGenericService<CategoryDto> {
    CategoryDto addOrFetch(CategoryDto categoryDto);
}
