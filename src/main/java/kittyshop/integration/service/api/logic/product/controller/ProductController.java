package kittyshop.integration.service.api.logic.product.controller;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.logic.common.controller.BaseController;
import kittyshop.integration.service.api.logic.common.dto.Response;
import kittyshop.integration.service.api.logic.product.dto.ProductCreateRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductRequestDto;
import kittyshop.integration.service.api.logic.product.dto.ProductResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductUpdateRequestDto;
import kittyshop.integration.service.api.logic.product.mapper.ProductMapper;
import kittyshop.integration.service.api.logic.product.service.ProductService;
import kittyshop.integration.service.api.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final ProductService productService;

    @GetMapping(ControllerRoutes.PRODUCTS_GET)
    public ResponseEntity<Response> getAllProducts(
            @Valid ProductRequestDto requestDto,
            @PageableDefault Pageable pageable) {
        log.info("Find all products by request '{}' and pageable: {}",
                requestDto, pageable.toString());
        return this.response(productService.findAll(requestDto,
                PageableUtils.generatePageable(pageable.getPageNumber(),
                        pageable.getPageSize(), productService.getSortOrder())));
    }

    @GetMapping(ControllerRoutes.PRODUCT_GET)
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        log.info("Find product by id: {}", id);
        ProductResponseDto product = productService.findById(id);
        return this.response(product);
    }

    @PostMapping(ControllerRoutes.PRODUCT_CREATE)
    public ResponseEntity<Response> createProduct(@Valid @RequestBody ProductCreateRequestDto createProductDto) {
        log.info("Create product with data: {}", createProductDto);
        return this.response(HttpStatus.CREATED.value(), productService.create(createProductDto));
    }

    @PatchMapping(ControllerRoutes.PRODUCT_UPDATE)
    public ResponseEntity<Response> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequestDto updateProductDto) {
        log.info("Update product with id: {}", id);
        return this.response(productService.updateById(id, updateProductDto));
    }

    @DeleteMapping(ControllerRoutes.PRODUCT_DELETE)
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        log.info("Delete product with id: {}", id);
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
