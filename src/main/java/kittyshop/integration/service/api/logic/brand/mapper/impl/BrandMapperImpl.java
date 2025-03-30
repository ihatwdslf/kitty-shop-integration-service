package kittyshop.integration.service.api.logic.brand.mapper.impl;

import jakarta.persistence.EntityNotFoundException;
import kittyshop.integration.service.api.logic.brand.dto.BrandCreateRequestDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandResponseDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandUpdateRequestDto;
import kittyshop.integration.service.api.logic.brand.entity.Brand;
import kittyshop.integration.service.api.logic.brand.mapper.BrandMapper;
import kittyshop.integration.service.api.logic.reference.entity.Country;
import kittyshop.integration.service.api.logic.reference.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class BrandMapperImpl implements BrandMapper {

    private final CountryService countryService;

    @Override
    public Brand toBrand(BrandCreateRequestDto createRequestDto) {
        if (createRequestDto == null) {
            return null;
        }

        Brand brand = new Brand();
        brand.setName(createRequestDto.getName());
        brand.setDescription(createRequestDto.getDescription());
        brand.setWebsite(createRequestDto.getWebsite());

        Country brandCountry = countryService.findByCode(createRequestDto.getCountryCode())
                .orElseThrow(() -> new EntityNotFoundException("Country not found by code: " + createRequestDto.getCountryCode()));
        brand.setCountry(brandCountry);

        log.info("Mapped brand entity (from create request): {}", brand);
        return brand;
    }

    @Override
    public BrandResponseDto toBrandResponseDto(Brand brand) {
        if (brand == null) {
            return null;
        }

        BrandResponseDto brandResponseDto = new BrandResponseDto();
        brandResponseDto.setId(brand.getId());
        brandResponseDto.setName(brand.getName());
        brandResponseDto.setDescription(brand.getDescription());
        brandResponseDto.setWebsite(brand.getWebsite());
        brandResponseDto.setCountry(brand.getCountry());

        log.info("Mapped brand response: {}", brandResponseDto);
        return brandResponseDto;
    }

    @Override
    public void updateBrand(BrandUpdateRequestDto updateRequestDto, Brand brand) {
        if (updateRequestDto == null || brand == null) {
            return;
        }
        
        if (updateRequestDto.getName() != null) {
            brand.setName(updateRequestDto.getName());
        }
        
        if (updateRequestDto.getDescription() != null) {
            brand.setDescription(updateRequestDto.getDescription());
        }
        
        if (updateRequestDto.getWebsite() != null) {
            brand.setWebsite(updateRequestDto.getWebsite());
        }
        
        if (updateRequestDto.getCountryCode() != null) {
            Country brandCountry = countryService.findByCode(updateRequestDto.getCountryCode())
                    .orElseThrow(() -> new EntityNotFoundException("Country not found by code: " + updateRequestDto.getCountryCode()));
            brand.setCountry(brandCountry);
        }
        
        log.info("Mapped updated brand: {}", brand);
    }
}
