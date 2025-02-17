package kittyshop.integration.service.api.controller.details;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends BaseController {

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long brandId) {
        return notImplementedEndpoint();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return notImplementedEndpoint();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Object createProductDto) {
        return notImplementedEndpoint();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Object updateProductDto) {
        return notImplementedEndpoint();
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<?> updateStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        return notImplementedEndpoint();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return notImplementedEndpoint();
    }
}
