package kittyshop.integration.service.api.logic.category.mapper.impl;

import jakarta.persistence.EntityNotFoundException;
import kittyshop.integration.service.api.logic.category.dto.CategoryCreateRequestDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryResponseDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryUpdateRequestDto;
import kittyshop.integration.service.api.logic.category.entity.Category;
import kittyshop.integration.service.api.logic.category.mapper.CategoryMapper;
import kittyshop.integration.service.api.logic.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

    private final CategoryRepository categoryService;

    @Override
    public Category toCategory(CategoryCreateRequestDto createRequestDto) {
        if (createRequestDto == null) {
            return null;
        }

        Category category = new Category();
        category.setKey(createRequestDto.getKey());
        category.setName(createRequestDto.getName());
        category.setDescription(createRequestDto.getDescription());

        category.setIsRemovable(createRequestDto.getIsRemovable() != null
                ? createRequestDto.getIsRemovable()
                : true
        );

        category.setIsQuicklyAccessible(createRequestDto.getIsQuicklyAccessible() != null
                ? createRequestDto.getIsQuicklyAccessible()
                : false
        );

        if (createRequestDto.getIcon() != null) {
            category.setIcon(createRequestDto.getIcon());
        }

        if (createRequestDto.getParentId() != null) {
            Category parentCategory = categoryService.findById(createRequestDto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent category not found by id: " + createRequestDto.getParentId()));
            category.setParent(parentCategory);
        }

        log.info("Mapped category entity (from create request): {}", category);
        return category;
    }

    @Override
    public CategoryResponseDto toCategoryResponseDto(Category category) {
        if (category == null) {
            return null;
        }

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setKey(category.getKey());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());
        categoryResponseDto.setIcon(category.getIcon());
        categoryResponseDto.setIsRemovable(category.getIsRemovable());
        categoryResponseDto.setIsQuicklyAccessible(category.getIsQuicklyAccessible());

        if (category.getParent() != null) {
            categoryResponseDto.setParentId(category.getParent().getId());
        }

        log.info("Mapped category response: {}", categoryResponseDto);
        return categoryResponseDto;
    }

    @Override
    public void updateCategory(CategoryUpdateRequestDto updateRequestDto, Category category) {
        if (updateRequestDto == null || category == null) {
            return;
        }
        
        if (updateRequestDto.getKey() != null) {
            category.setKey(updateRequestDto.getKey());
        }

        if (updateRequestDto.getName() != null) {
            category.setName(updateRequestDto.getName());
        }

        if (updateRequestDto.getDescription() != null) {
            category.setDescription(updateRequestDto.getDescription());
        }

        if (updateRequestDto.getIcon() != null) {
            category.setIcon(updateRequestDto.getIcon());
        }

        if (updateRequestDto.getIsRemovable() != null) {
            category.setIsRemovable(updateRequestDto.getIsRemovable());
        }
        
        if (updateRequestDto.getIsQuicklyAccessible() != null) {
            category.setIsQuicklyAccessible(updateRequestDto.getIsQuicklyAccessible());
        }

        if (updateRequestDto.getParentId() != null) {
            Category parentCategory = categoryService.findById(updateRequestDto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent category not found by id: " + updateRequestDto.getParentId()));
            category.setParent(parentCategory);
        }

        log.info("Mapped updated category: {}", category);
    }
}
