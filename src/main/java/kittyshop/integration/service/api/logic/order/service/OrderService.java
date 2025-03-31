package kittyshop.integration.service.api.logic.order.service;

import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.common.service.SortableService;
import kittyshop.integration.service.api.logic.order.dto.OrderCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderUpdateRequestDto;
import org.springframework.data.domain.Pageable;

public interface OrderService extends SortableService {

    OrderResponseDto create(String authorizedUserEmail, OrderCreateRequestDto createRequestDto);

    ListResponseDto<OrderResponseDto> findAll(String authorizedUserEmail, Pageable pageable);

    OrderResponseDto findById(Long id);

    void deleteById(Long id);

    OrderResponseDto updateById(Long id, OrderUpdateRequestDto requestDto);
}
