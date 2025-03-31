package kittyshop.integration.service.api.logic.order.mapper;

import kittyshop.integration.service.api.logic.order.dto.OrderCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.entity.Order;

public interface OrderMapper {

    Order toOrder(String authorizedUserEmail, OrderCreateRequestDto createRequestDto);

    Order toOrder(String authorizedUserEmail, OrderResponseDto responseDto);

    OrderResponseDto toOrderResponseDto(Order order);

    void updateOrder(OrderUpdateRequestDto updateRequestDto, Order order);
}
