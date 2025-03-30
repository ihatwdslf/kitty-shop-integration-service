package kittyshop.integration.service.api.logic.category.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import kittyshop.integration.service.api.logic.category.dto.CategoryCreateRequestDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryResponseDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryUpdateRequestDto;
import kittyshop.integration.service.api.logic.category.entity.Category;
import kittyshop.integration.service.api.logic.category.mapper.CategoryMapper;
import kittyshop.integration.service.api.logic.category.repository.CategoryRepository;
import kittyshop.integration.service.api.logic.category.service.CategoryService;
import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto create(CategoryCreateRequestDto requestDto) {
        if (categoryRepository.existsByName(requestDto.getName())) {
            throw new ValidationException(String.format("Category with this name '%s' already exists", requestDto.getName()));
        }

        Category category = categoryMapper.toCategory(requestDto); // leave it as it is: if add specific logic to other fields
        return categoryMapper.toCategoryResponseDto(categoryRepository.save(category));
    }

    @Override
    public ListResponseDto<CategoryResponseDto> findAll(Pageable pageable) {
        Page<Category> categoriesPage = categoryRepository.findAll(pageable);
        return new ListResponseDto<CategoryResponseDto>()
                .setList(categoriesPage.stream()
                        .map(categoryMapper::toCategoryResponseDto)
                        .toList())
                .setTotalRows(categoriesPage.getTotalElements());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDto updateById(Long id, CategoryUpdateRequestDto requestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with id '%s' not found", id))
        );
        categoryMapper.updateCategory(requestDto, category);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDto(category);
    }

    @Override
    public List<Sort.Order> getSortOrder() {
        return List.of(new Sort.Order(Sort.Direction.ASC, "id"));
    }
}
