package kittyshop.integration.service.api.logic.product.mapper.impl;

import jakarta.persistence.EntityNotFoundException;
import kittyshop.integration.service.api.logic.brand.dto.BrandIdentifierAndNameResponseDto;
import kittyshop.integration.service.api.logic.brand.entity.Brand;
import kittyshop.integration.service.api.logic.brand.service.BrandService;
import kittyshop.integration.service.api.logic.category.dto.CategoryIdentifierAndNameResponseDto;
import kittyshop.integration.service.api.logic.category.entity.Category;
import kittyshop.integration.service.api.logic.category.service.CategoryService;
import kittyshop.integration.service.api.logic.product.dto.ProductCreateRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductUpdateRequestDto;
import kittyshop.integration.service.api.logic.product.entity.Product;
import kittyshop.integration.service.api.logic.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {

    private final BrandService brandService;
    private final CategoryService categoryService;

    @Override
    public Product toProduct(ProductCreateRequestDto createRequestDto) {
        if (createRequestDto == null) {
            return null;
        }

        Product product = new Product();
        product.setName(createRequestDto.getName());
        product.setDescription(createRequestDto.getDescription());
        product.setCreatedAt(createRequestDto.getCreatedAt());
        product.setUpdatedAt(createRequestDto.getCreatedAt());
        product.setPrice(createRequestDto.getPrice());
        product.setImageUrl(createRequestDto.getImageUrl());
        product.setSku(createRequestDto.getStockKeepingUnit());
        product.setStockQuantity(createRequestDto.getStockQuantity());
        
        product.setDiscount(createRequestDto.getDiscount() != null ? createRequestDto.getDiscount() : 0);

        Brand productBrand = brandService.findById(createRequestDto.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found by id: " + createRequestDto.getBrandId()));
        product.setBrand(productBrand);

        Set<Category> categories = createRequestDto.getCategoryKeys().stream()
                .map(categoryKey -> categoryService.findByKey(categoryKey)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found by key: " + categoryKey))
                )
                .collect(Collectors.toSet());
        product.setCategories(categories);

        log.info("Mapped product entity (from create request): {}", product);
        return product;
    }

    @Override
    public Product toProduct(ProductResponseDto responseDto) {
        if (responseDto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(responseDto.getId());
        product.setName(responseDto.getName());
        product.setDescription(responseDto.getDescription());
        product.setCreatedAt(responseDto.getCreatedAt());
        product.setUpdatedAt(responseDto.getCreatedAt());
        product.setPrice(responseDto.getPrice());
        product.setDiscount(responseDto.getDiscount());
        product.setImageUrl(responseDto.getImageUrl());
        product.setSku(responseDto.getStockKeepingUnit());
        product.setStockQuantity(responseDto.getStockQuantity());

        Brand productBrand = brandService.findById(responseDto.getBrand().getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found by id: " + responseDto.getBrand().getBrandId()));
        product.setBrand(productBrand);

        Set<Category> categories = responseDto.getCategories().stream()
                .map(category -> categoryService.findById(category.getCategoryId())
                        .orElseThrow(() -> new EntityNotFoundException("Category not found by id: " + category.getCategoryId()))
                )
                .collect(Collectors.toSet());
        product.setCategories(categories);

        log.info("Mapped product entity (from response dto): {}", product);
        return product;
    }

    @Override
    public ProductResponseDto toProductResponseDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setCreatedAt(product.getCreatedAt());
        productResponseDto.setUpdatedAt(product.getUpdatedAt());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDiscount(product.getDiscount());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setStockQuantity(product.getStockQuantity());
        productResponseDto.setStockKeepingUnit(product.getSku());

        List<CategoryIdentifierAndNameResponseDto> categories = product.getCategories().stream()
                .map(category -> new CategoryIdentifierAndNameResponseDto()
                        .setCategoryId(category.getId())
                        .setCategoryKey(category.getKey())
                        .setCategoryName(category.getName()))
                .toList();
        productResponseDto.setCategories(categories);

        BrandIdentifierAndNameResponseDto brand = new BrandIdentifierAndNameResponseDto()
                .setBrandId(product.getBrand().getId())
                .setBrandName(product.getBrand().getName());
        productResponseDto.setBrand(brand);

        log.info("Mapped product response: {}", productResponseDto);
        return productResponseDto;
    }

    @Override
    public void updateProduct(ProductUpdateRequestDto updateRequestDto, Product product) {
        if (updateRequestDto == null || product == null) {
            return;
        }

        if (updateRequestDto.getName() != null) {
            product.setName(updateRequestDto.getName());
        }

        if (updateRequestDto.getDescription() != null) {
            product.setDescription(updateRequestDto.getDescription());
        }

        if (updateRequestDto.getCreatedAt() != null) {
            product.setCreatedAt(updateRequestDto.getCreatedAt());
        }

        if (updateRequestDto.getUpdatedAt() != null) {
            product.setUpdatedAt(updateRequestDto.getUpdatedAt());
        }

        if (updateRequestDto.getPrice() != null) {
            product.setPrice(updateRequestDto.getPrice());
        }
        
        if (updateRequestDto.getDiscount() != null) {
            product.setDiscount(updateRequestDto.getDiscount());
        }
        
        if (updateRequestDto.getImageUrl() != null) {
            product.setImageUrl(updateRequestDto.getImageUrl());
        }

        if (updateRequestDto.getStockQuantity() != null) {
            product.setStockQuantity(updateRequestDto.getStockQuantity());
        }

        if (updateRequestDto.getStockKeepingUnit() != null) {
            product.setSku(updateRequestDto.getStockKeepingUnit());
        }

        if (updateRequestDto.getCategoryKeys() != null) {
            Set<Category> categories = updateRequestDto.getCategoryKeys().stream()
                    .map(categoryKey -> categoryService.findByKey(categoryKey)
                            .orElseThrow(() -> new EntityNotFoundException("Category not found by key: " + categoryKey))
                    )
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }

        if (updateRequestDto.getBrandId() != null) {
            Brand productBrand = brandService.findById(updateRequestDto.getBrandId())
                    .orElseThrow(() -> new EntityNotFoundException("Brand not found by id: " + updateRequestDto.getBrandId()));
            product.setBrand(productBrand);
        }
    }
}
