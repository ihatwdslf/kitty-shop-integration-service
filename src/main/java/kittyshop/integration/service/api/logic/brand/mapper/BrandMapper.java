package kittyshop.integration.service.api.logic.brand.mapper;

import kittyshop.integration.service.api.logic.brand.dto.BrandCreateRequestDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandResponseDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandUpdateRequestDto;
import kittyshop.integration.service.api.logic.brand.entity.Brand;

public interface BrandMapper {

    Brand toBrand(BrandCreateRequestDto createRequestDto);
    
    BrandResponseDto toBrandResponseDto(Brand brand);

    void updateBrand(BrandUpdateRequestDto updateRequestDto, Brand brand);
}
