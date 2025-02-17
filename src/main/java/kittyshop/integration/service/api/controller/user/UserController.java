package kittyshop.integration.service.api.controller.user;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController {

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return notImplementedEndpoint();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return notImplementedEndpoint();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody Object createUserDto) {
        return notImplementedEndpoint();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody Object updateUserDto) {
        return notImplementedEndpoint();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return notImplementedEndpoint();
    }
}
