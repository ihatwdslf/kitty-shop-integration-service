package kittyshop.integration.service.api.logic.brand.service;

import kittyshop.integration.service.api.logic.brand.dto.BrandCreateRequestDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandResponseDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandUpdateRequestDto;
import kittyshop.integration.service.api.logic.brand.entity.Brand;
import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.common.service.SortableService;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BrandService extends SortableService {

    BrandResponseDto create(BrandCreateRequestDto requestDto);

    ListResponseDto<BrandResponseDto> findAll(Pageable pageable);

    Optional<Brand> findById(Long id);

    void deleteById(Long id);

    BrandResponseDto updateById(Long id, BrandUpdateRequestDto requestDto);
}
