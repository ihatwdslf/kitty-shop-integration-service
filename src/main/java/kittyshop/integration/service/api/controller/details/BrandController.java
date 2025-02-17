package kittyshop.integration.service.api.controller.details;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController extends BaseController {

    @GetMapping
    public ResponseEntity<?> getAllBrands() {
        return notImplementedEndpoint();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id) {
        return notImplementedEndpoint();
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@Valid @RequestBody Object createBrandDto) {
        return notImplementedEndpoint();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody Object updateBrandDto) {
        return notImplementedEndpoint();
    }
}
