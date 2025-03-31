package kittyshop.integration.service.api.logic.brand.controller;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.logic.brand.dto.BrandCreateRequestDto;
import kittyshop.integration.service.api.logic.brand.dto.BrandUpdateRequestDto;
import kittyshop.integration.service.api.logic.brand.entity.Brand;
import kittyshop.integration.service.api.logic.brand.mapper.BrandMapper;
import kittyshop.integration.service.api.logic.brand.service.BrandService;
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
public class BrandController extends BaseController {

    private final BrandMapper brandMapper;
    private final BrandService brandService;

    @GetMapping(ControllerRoutes.BRANDS_GET)
    public ResponseEntity<Response> getAllBrands(@PageableDefault Pageable pageable) {
        log.info("Find all brands by pageable: {}", pageable.toString());
        return this.response(brandService.findAll(
                PageableUtils.generatePageable(pageable.getPageNumber(),
                        pageable.getPageSize(), brandService.getSortOrder())));
    }

    @GetMapping(ControllerRoutes.BRAND_GET)
    public ResponseEntity<Response> getBrandById(@PathVariable Long id) {
        log.info("Find brand by id: {}", id);
        Optional<Brand> userOptional = brandService.findById(id);
        return userOptional.map(category -> this.response(brandMapper.toBrandResponseDto(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(ControllerRoutes.BRAND_CREATE)
    public ResponseEntity<Response> createBrand(@Valid @RequestBody BrandCreateRequestDto createBrandDto) {
        log.info("Create brand with data: {}", createBrandDto);
        return this.response(HttpStatus.CREATED.value(), brandService.create(createBrandDto));
    }

    @PatchMapping(ControllerRoutes.BRAND_UPDATE)
    public ResponseEntity<Response> updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody BrandUpdateRequestDto updateBrandDto) {
        log.info("Update brand with id: {}", id);
        return this.response(brandService.updateById(id, updateBrandDto));
    }

    @DeleteMapping(ControllerRoutes.BRAND_DELETE)
    public ResponseEntity<Response> deleteBrand(@PathVariable Long id) {
        log.info("Delete brand with id: {}", id);
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
