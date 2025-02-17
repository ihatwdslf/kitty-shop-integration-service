package kittyshop.integration.service.api.controller.details;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController {

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return notImplementedEndpoint();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return notImplementedEndpoint();
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody Object createCategoryDto) {
        return notImplementedEndpoint();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody Object updateCategoryDto) {
        return notImplementedEndpoint();
    }
}
