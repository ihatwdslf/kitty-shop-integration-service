package kittyshop.integration.service.api.logic.category.mapper;

import kittyshop.integration.service.api.logic.category.dto.CategoryCreateRequestDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryResponseDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryUpdateRequestDto;
import kittyshop.integration.service.api.logic.category.entity.Category;

public interface CategoryMapper {

    Category toCategory(CategoryCreateRequestDto createRequestDto);

    CategoryResponseDto toCategoryResponseDto(Category category);

    void updateCategory(CategoryUpdateRequestDto updateRequestDto, Category category);
}
