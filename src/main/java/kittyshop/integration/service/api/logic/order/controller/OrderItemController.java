package kittyshop.integration.service.api.logic.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.logic.common.controller.BaseController;
import kittyshop.integration.service.api.logic.common.dto.Response;
import kittyshop.integration.service.api.logic.order.dto.OrderItemCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.service.OrderItemService;
import kittyshop.integration.service.api.security.JwtUtil;
import kittyshop.integration.service.api.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
public class OrderItemController extends BaseController {

    private final JwtUtil jwtUtil;
    private final OrderItemService orderItemService;

    @GetMapping(ControllerRoutes.ORDER_ITEMS_GET)
    public ResponseEntity<Response> getAllOrderItems(
            @PathVariable Long orderId,
            @PageableDefault Pageable pageable) {
        log.info("Find all order items by user id '{}' and pageable: {}", orderId, pageable.toString());
        return this.response(orderItemService.findAll(orderId,
                PageableUtils.generatePageable(pageable.getPageNumber(),
                        pageable.getPageSize(), orderItemService.getSortOrder())));
    }

    @GetMapping(ControllerRoutes.ORDER_ITEM_GET)
    public ResponseEntity<Response> getOrderItemById(@PathVariable Long orderId, @PathVariable Long productId) {
        log.info("Find order item by order id '{}' and product id '{}'", orderId, productId);
        OrderItemResponseDto orderItem = orderItemService.findByOrderAndProductIds(orderId, productId);
        return this.response(orderItem);
    }

    @PostMapping(ControllerRoutes.ORDER_ITEM_ADD)
    public ResponseEntity<Response> addOrderItem(@PathVariable Long orderId,
                                                 @RequestBody @Valid OrderItemCreateRequestDto createRequestDto,
                                                 HttpServletRequest httpServletRequest) {
        String authorizedUserEmail = jwtUtil.getAuthorizedUserEmailFromJwt(httpServletRequest);
        log.info("Create order with orderId '{}', user email '{}' and data: {}",
                orderId, authorizedUserEmail, createRequestDto);
        return this.response(orderItemService.create(authorizedUserEmail, orderId, createRequestDto));
    }

    @PatchMapping(ControllerRoutes.ORDER_ITEM_UPDATE)
    public ResponseEntity<Response> updateOrderItem(@PathVariable Long orderId,
                                                    @PathVariable Long productId,
                                                    @RequestBody @Valid OrderItemUpdateRequestDto updateRequestDto) {
        log.info("Update order item with order id '{}', product id '{}' and data: {}",
                orderId, productId, updateRequestDto);
        return this.response(orderItemService.updateById(orderId, productId, updateRequestDto));
    }

    @DeleteMapping(ControllerRoutes.ORDER_ITEM_REMOVE)
    public ResponseEntity<Response> removeOrderItem(@PathVariable Long orderId,
                                                    @PathVariable Long productId) {
        log.info("Delete order item with order id '{}' and product id '{}'", orderId, productId);
        orderItemService.deleteById(orderId, productId);
        return ResponseEntity.noContent().build();
    }
}
