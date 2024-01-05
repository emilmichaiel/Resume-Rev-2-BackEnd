package com.emilmi.resume.project;

import com.emilmi.resume.category.Category;
import com.emilmi.resume.category.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectCategoryMapper {
    private final CategoryRepository categoryRepository;

    public ProjectCategoryMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @CategoryLookup
    public List<Category> map(List<String> categoryNames) {
        List<Category> categoryList = new ArrayList<>();
        categoryNames.forEach(name -> {
            Category category = categoryRepository.findByName(name)
                    .orElse(
                            new Category(null, name)
                    );
            if (category.getId() == null) {
                categoryRepository.save(category);
            }
            categoryList.add(
                    category
            );
        });
        return categoryList;
    }
}
