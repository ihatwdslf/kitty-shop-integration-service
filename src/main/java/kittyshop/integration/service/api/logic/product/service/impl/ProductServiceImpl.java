package kittyshop.integration.service.api.logic.product.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.validation.ValidationException;
import kittyshop.integration.service.api.logic.category.entity.Category;
import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductCreateRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductUpdateRequestDto;
import kittyshop.integration.service.api.logic.product.entity.Product;
import kittyshop.integration.service.api.logic.product.mapper.ProductMapper;
import kittyshop.integration.service.api.logic.product.repository.ProductRepository;
import kittyshop.integration.service.api.logic.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto create(ProductCreateRequestDto requestDto) {
        if (productRepository.existsByName(requestDto.getName())) {
            throw new ValidationException(String.format("Product with this name '%s' already exists", requestDto.getName()));
        }

        Product product = productMapper.toProduct(requestDto); // leave it as it is: if add specific logic to other fields
        return productMapper.toProductResponseDto(productRepository.save(product));
    }

    @Override
    public ListResponseDto<ProductResponseDto> findAll(ProductRequestDto requestDto, Pageable pageable) {
        Specification<Product> spec = Specification.where(null);

        if (requestDto.getCategoryIds() != null && !requestDto.getCategoryIds().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                Join<Product, Category> categoryJoin = root.join("categories");
                return categoryJoin.get("id").in(requestDto.getCategoryIds());
            });
        }

        if (requestDto.getBrandId() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("brand").get("id"), requestDto.getBrandId()));
        }

        Page<Product> productsPage = productRepository.findAll(spec, pageable);

        return new ListResponseDto<ProductResponseDto>()
                .setList(productsPage.stream()
                        .map(productMapper::toProductResponseDto)
                        .toList())
                .setTotalRows(productsPage.getTotalElements());
    }

    @Override
    public ProductResponseDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDto updateById(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Product with id '%s' not found", id))
        );
        productMapper.updateProduct(requestDto, product);
        productRepository.save(product);
        return productMapper.toProductResponseDto(product);
    }

    @Override
    public List<Sort.Order> getSortOrder() {
        return List.of(new Sort.Order(Sort.Direction.ASC, "id"));
    }
}
