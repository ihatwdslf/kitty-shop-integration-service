package kittyshop.integration.service.api.logic.order.mapper.impl;

import kittyshop.integration.service.api.logic.order.dto.OrderItemCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.entity.Order;
import kittyshop.integration.service.api.logic.order.entity.OrderItem;
import kittyshop.integration.service.api.logic.order.entity.OrderItemId;
import kittyshop.integration.service.api.logic.order.mapper.OrderItemMapper;
import kittyshop.integration.service.api.logic.order.mapper.OrderMapper;
import kittyshop.integration.service.api.logic.order.service.OrderService;
import kittyshop.integration.service.api.logic.product.entity.Product;
import kittyshop.integration.service.api.logic.product.mapper.ProductMapper;
import kittyshop.integration.service.api.logic.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class OrderItemMapperImpl implements OrderItemMapper {

    private final ProductMapper productMapper;
    private final ProductService productService;

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Override
    public OrderItem toOrderItem(String authorizedUserEmail, Long orderId, OrderItemCreateRequestDto createRequestDto) {
        if (createRequestDto == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(createRequestDto.getQuantity());

        Product product = productMapper.toProduct(productService.findById(createRequestDto.getProductId()));
        orderItem.setProduct(product);

        Order order = orderMapper.toOrder(authorizedUserEmail, orderService.findById(orderId));
        orderItem.setOrder(order);

        OrderItemId orderItemId = new OrderItemId()
                .setOrderId(order.getId())
                .setProductId(product.getId());
        orderItem.setId(orderItemId);

        return orderItem;
    }

    @Override
    public OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();

        orderItemResponseDto.setProductId(orderItem.getId().getProductId());
        orderItemResponseDto.setQuantity(orderItem.getQuantity());

        return orderItemResponseDto;
    }

    @Override
    public void updateOrderItem(OrderItemUpdateRequestDto updateRequestDto, OrderItem orderItem) {
        if (updateRequestDto == null || orderItem == null) {
            return;
        }

        if (updateRequestDto.getQuantity() != null) {
            orderItem.setQuantity(updateRequestDto.getQuantity());
        }
    }
}
