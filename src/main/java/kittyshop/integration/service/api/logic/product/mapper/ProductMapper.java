package kittyshop.integration.service.api.logic.product.mapper;

import kittyshop.integration.service.api.logic.product.dto.ProductCreateRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductUpdateRequestDto;
import kittyshop.integration.service.api.logic.product.entity.Product;

public interface ProductMapper {

    Product toProduct(ProductCreateRequestDto createRequestDto);
    
    Product toProduct(ProductResponseDto responseDto);

    ProductResponseDto toProductResponseDto(Product product);

    void updateProduct(ProductUpdateRequestDto updateRequestDto, Product product);
}
