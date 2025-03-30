package kittyshop.integration.service.api.logic.brand.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import kittyshop.integration.service.api.logic.brand.dto.BrandCreateRequestDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandResponseDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandUpdateRequestDto;
import kittyshop.integration.service.api.logic.brand.entity.Brand;
import kittyshop.integration.service.api.logic.brand.mapper.BrandMapper;
import kittyshop.integration.service.api.logic.brand.repository.BrandRepository;
import kittyshop.integration.service.api.logic.brand.service.BrandService;
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
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;

    @Override
    public BrandResponseDto create(BrandCreateRequestDto requestDto) {
        if (brandRepository.existsByName(requestDto.getName())) {
            throw new ValidationException(String.format("Brand with this name '%s' already exists", requestDto.getName()));
        }

        Brand brand = brandMapper.toBrand(requestDto); // leave it as it is: if add specific logic to other fields
        return brandMapper.toBrandResponseDto(brandRepository.save(brand));
    }

    @Override
    public ListResponseDto<BrandResponseDto> findAll(Pageable pageable) {
        Page<Brand> brandsPage = brandRepository.findAll(pageable);
        return new ListResponseDto<BrandResponseDto>()
                .setList(brandsPage.stream()
                        .map(brandMapper::toBrandResponseDto)
                        .toList())
                .setTotalRows(brandsPage.getTotalElements());
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public BrandResponseDto updateById(Long id, BrandUpdateRequestDto requestDto) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Brand with id '%s' not found", id))
        );
        brandMapper.updateBrand(requestDto, brand);
        brandRepository.save(brand);
        return brandMapper.toBrandResponseDto(brand);
    }

    @Override
    public List<Sort.Order> getSortOrder() {
        return List.of(new Sort.Order(Sort.Direction.ASC, "id"));
    }
}
