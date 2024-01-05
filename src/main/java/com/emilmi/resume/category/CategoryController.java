package com.emilmi.resume.category;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id.ASC") String sort
    ) {
        return categoryService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto findCategoryById(
            @PathVariable String id
    ) {
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        return categoryService.add(categoryDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryDto updateCategory(
            @PathVariable String id,
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        return categoryService.update(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @PathVariable String id
    ) {
        categoryService.delete(id);
    }

    @PostMapping("/addOrFetch")
    public CategoryDto addOrFetchExistingCategory(
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        return categoryService.addOrFetch(categoryDto);
    }
}
