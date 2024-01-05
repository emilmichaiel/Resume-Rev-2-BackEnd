package com.emilmi.resume.category;

import com.emilmi.resume.common.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final Common<Category> _common;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, Common<Category> common) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        _common = common;
    }

    @Override
    public Map<String, Object> findAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, _common.parseSort(sort));
        Page<Category> pageCategories = categoryRepository.findAll(paging);

        List<CategoryDto> categoryResponseList = pageCategories.getContent()
                .stream()
                .map(categoryMapper::toDto)
                .toList();

        return _common.generatePageResponse(
                size,
                pageCategories,
                categoryResponseList,
                Category.class
        );
    }

    @Override
    public CategoryDto findById(String id) {
        return categoryMapper.toDto(
                this._common.getById(categoryRepository, id)
        );
    }

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        return categoryMapper.toDto(
                categoryRepository.save(
                        categoryMapper.toDocument(categoryDto)
                )
        );
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String id) {
        this._common.getById(categoryRepository, id);

        return categoryMapper.toDto(
                categoryRepository.save(
                        categoryMapper.toDocument(categoryDto, id)
                )
        );
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto addOrFetch(CategoryDto categoryDto) {
        Category category = categoryRepository.findByName(categoryDto.name().toLowerCase().trim())
                .orElse(
                        categoryMapper.toDocument(categoryDto)
                );

        return category.getId() == null ?
                categoryMapper.toDto(
                        categoryRepository.save(category)
                ) :
                categoryMapper.toDto(category);
    }
}
