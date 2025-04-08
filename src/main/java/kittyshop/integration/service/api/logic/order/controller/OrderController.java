package kittyshop.integration.service.api.logic.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.logic.common.controller.BaseController;
import kittyshop.integration.service.api.logic.common.dto.Response;
import kittyshop.integration.service.api.logic.order.dto.OrderCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.service.OrderService;
import kittyshop.integration.service.api.security.JwtUtil;
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
public class OrderController extends BaseController {

    private final JwtUtil jwtUtil;
    private final OrderService orderService;

    @GetMapping(ControllerRoutes.ORDERS_GET)
    public ResponseEntity<Response> getAllOrders(@PageableDefault Pageable pageable,
                                                 @RequestParam(required = false) String status,
                                                 HttpServletRequest httpServletRequest) {
        String authorizedUserEmail = jwtUtil.getAuthorizedUserEmailFromJwt(httpServletRequest);
        log.info("Find all orders by user email '{}' and pageable: {}, status: {}", 
                authorizedUserEmail, pageable.toString(), status);
        return this.response(orderService.findAll(
                authorizedUserEmail,
                PageableUtils.generatePageable(pageable.getPageNumber(),
                        pageable.getPageSize(), orderService.getSortOrder()), 
                status
        ));
    }

    @GetMapping(ControllerRoutes.ORDER_GET)
    public ResponseEntity<Response> getOrderById(@PathVariable Long id) {
        log.info("Find order by id: {}", id);
        OrderResponseDto order = orderService.findById(id);
        return this.response(order);
    }

    @PostMapping(ControllerRoutes.ORDER_CREATE)
    public ResponseEntity<Response> createOrder(@RequestBody @Valid OrderCreateRequestDto createRequestDto,
                                                HttpServletRequest httpServletRequest) {
        String authorizedUserEmail = jwtUtil.getAuthorizedUserEmailFromJwt(httpServletRequest);
        log.info("Create order with user email '{}' and data: {}", authorizedUserEmail, createRequestDto);
        return this.response(HttpStatus.CREATED.value(), orderService.create(authorizedUserEmail, createRequestDto));
    }

    @PatchMapping(ControllerRoutes.ORDER_UPDATE)
    public ResponseEntity<Response> updateOrder(@PathVariable Long id,
                                                @RequestBody @Valid OrderUpdateRequestDto updateRequestDto) {
        log.info("Update order with id: {}", id);
        return this.response(orderService.updateById(id, updateRequestDto));
    }

    @DeleteMapping(ControllerRoutes.ORDER_DELETE)
    public ResponseEntity<Response> deleteOrder(@PathVariable Long id) {
        log.info("Delete order with id: {}", id);
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
