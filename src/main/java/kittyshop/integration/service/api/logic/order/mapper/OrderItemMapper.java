package kittyshop.integration.service.api.logic.order.mapper;

import kittyshop.integration.service.api.logic.order.dto.OrderItemCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.entity.OrderItem;

public interface OrderItemMapper {
    OrderItem toOrderItem(String authorizedUserEmail, Long orderId, OrderItemCreateRequestDto createRequestDto);

    OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem);

    void updateOrderItem(OrderItemUpdateRequestDto updateRequestDto, OrderItem orderItem);
}
