package kittyshop.integration.service.api.logic.product.service;

import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.common.service.SortableService;
import kittyshop.integration.service.api.logic.product.dto.ProductGetTotalsRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductGetTotalsResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductCreateRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductUpdateRequestDto;
import org.springframework.data.domain.Pageable;

public interface ProductService extends SortableService {

    ProductResponseDto create(ProductCreateRequestDto requestDto);

    ListResponseDto<ProductResponseDto> findAll(ProductRequestDto requestDto, Pageable pageable);

    ProductResponseDto findById(Long id);

    void deleteById(Long id);

    ProductResponseDto updateById(Long id, ProductUpdateRequestDto requestDto);
    
    ProductGetTotalsResponseDto findTotals(ProductGetTotalsRequestDto requestDto);
}
