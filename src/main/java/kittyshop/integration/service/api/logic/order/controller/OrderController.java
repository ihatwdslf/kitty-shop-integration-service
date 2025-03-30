package kittyshop.integration.service.api.logic.order.controller;

import jakarta.validation.Valid;
import kittyshop.integration.service.api.logic.common.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends BaseController {

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return notImplementedEndpoint();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return notImplementedEndpoint();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId) {
        return notImplementedEndpoint();
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody Object createOrderDto) {
        return notImplementedEndpoint();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam Long statusId) {
        return notImplementedEndpoint();
    }
}
