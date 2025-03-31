package kittyshop.integration.service.api.logic.order.service;

import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.common.service.SortableService;
import kittyshop.integration.service.api.logic.order.dto.OrderItemCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemUpdateRequestDto;
import org.springframework.data.domain.Pageable;

public interface OrderItemService extends SortableService {

    OrderItemResponseDto create(String authorizedUserEmail, Long orderId, OrderItemCreateRequestDto createRequestDto);

    ListResponseDto<OrderItemResponseDto> findAll(Long orderId, Pageable pageable);

    OrderItemResponseDto findByOrderAndProductIds(Long orderId, Long productId);

    void deleteById(Long orderId, Long productId);

    OrderItemResponseDto updateById(Long orderId, Long productId, OrderItemUpdateRequestDto requestDto);
}
