package kittyshop.integration.service.api.logic.product.controller;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.logic.common.controller.BaseController;
import kittyshop.integration.service.api.logic.common.dto.Response;
import kittyshop.integration.service.api.logic.order.dto.OrderItemCreateRequestDto;
import kittyshop.integration.service.api.logic.product.dto.*;
import kittyshop.integration.service.api.logic.product.service.ProductService;
import kittyshop.integration.service.api.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping(ControllerRoutes.PRODUCT_TOTALS)
    public ResponseEntity<Response> getProductById(
            @RequestParam Map<String, String> params) {
        Set<OrderItemCreateRequestDto> cartItems = new HashSet<>();

        // Parsing the cartItems parameters from the URL
        for (int i = 0; i < params.size() / 2; i++) {
            Long productId = Long.valueOf(params.get("cartItems[" + i + "].productId"));
            Integer quantity = Integer.valueOf(params.get("cartItems[" + i + "].quantity"));

            // Add to cartItems list
            cartItems.add(new OrderItemCreateRequestDto(productId, quantity));
        }

        // Construct your request DTO
        ProductGetTotalsRequestDto requestDto = new ProductGetTotalsRequestDto();
        requestDto.setCartItems(cartItems);

        // Call your service method
        ProductGetTotalsResponseDto product = productService.findTotals(requestDto);
        return this.response(product);
    }

    private Set<OrderItemCreateRequestDto> parseCartItems(String[] cartItems) {
        Set<OrderItemCreateRequestDto> cartItemsSet = new HashSet<>();

        for (String item : cartItems) {
            String[] parts = item.split(":");
            if (parts.length == 2) {
                Long productId = Long.valueOf(parts[0]);
                Integer quantity = Integer.valueOf(parts[1]);

                OrderItemCreateRequestDto dto = new OrderItemCreateRequestDto(productId, quantity);
                cartItemsSet.add(dto);
            }
        }

        return cartItemsSet;
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
