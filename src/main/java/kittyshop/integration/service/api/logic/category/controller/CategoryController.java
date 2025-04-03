package kittyshop.integration.service.api.logic.category.controller;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.logic.category.dto.CategoryCreateRequestDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryUpdateRequestDto;
import kittyshop.integration.service.api.logic.category.entity.Category;
import kittyshop.integration.service.api.logic.category.mapper.CategoryMapper;
import kittyshop.integration.service.api.logic.category.service.CategoryService;
import kittyshop.integration.service.api.logic.common.controller.BaseController;
import kittyshop.integration.service.api.logic.common.dto.Response;
import kittyshop.integration.service.api.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @GetMapping(ControllerRoutes.CATEGORIES_GET)
    public ResponseEntity<Response> getAllCategories(@PageableDefault Pageable pageable) {
        log.info("Find all categories by pageable: {}", pageable.toString());
        return this.response(categoryService.findAll(
                PageableUtils.generatePageable(pageable.getPageNumber(),
                        pageable.getPageSize(), categoryService.getSortOrder())));
    }

    @GetMapping(ControllerRoutes.CATEGORY_GET)
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id) {
        log.info("Find category by id: {}", id);
        Optional<Category> categoryOptional = categoryService.findById(id);
        return categoryOptional.map(category -> this.response(categoryMapper.toCategoryResponseDto(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping(ControllerRoutes.CATEGORY_NESTED_GET)
    public ResponseEntity<Response> getCategoryNestedById(@PathVariable Long id) {
        log.info("Find category nested by id: {}", id);
        return this.response(categoryService.findAllByParentId(id));
    }

    @PostMapping(ControllerRoutes.CATEGORY_CREATE)
    public ResponseEntity<Response> createCategory(@Valid @RequestBody CategoryCreateRequestDto createCategoryDto) {
        log.info("Create category with data: {}", createCategoryDto);
        return this.response(HttpStatus.CREATED.value(), categoryService.create(createCategoryDto));
    }

    @PatchMapping(ControllerRoutes.CATEGORY_UPDATE)
    public ResponseEntity<Response> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateRequestDto updateCategoryDto) {
        log.info("Update category with id: {}", id);
        return this.response(categoryService.updateById(id, updateCategoryDto));
    }

    @DeleteMapping(ControllerRoutes.CATEGORY_DELETE)
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id) {
        log.info("Delete category with id: {}", id);
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
