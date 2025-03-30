package kittyshop.integration.service.api.logic.category.service;

import kittyshop.integration.service.api.logic.category.dto.CategoryCreateRequestDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryResponseDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryUpdateRequestDto;
import kittyshop.integration.service.api.logic.category.entity.Category;
import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.common.service.SortableService;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService extends SortableService {

    CategoryResponseDto create(CategoryCreateRequestDto requestDto);

    ListResponseDto<CategoryResponseDto> findAll(Pageable pageable);

    Optional<Category> findById(Long id);

    void deleteById(Long id);

    CategoryResponseDto updateById(Long id, CategoryUpdateRequestDto requestDto);
}
