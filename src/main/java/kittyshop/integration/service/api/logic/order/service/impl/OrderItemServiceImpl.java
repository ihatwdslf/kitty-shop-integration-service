package kittyshop.integration.service.api.logic.order.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.entity.OrderItem;
import kittyshop.integration.service.api.logic.order.mapper.OrderItemMapper;
import kittyshop.integration.service.api.logic.order.repository.OrderItemRepository;
import kittyshop.integration.service.api.logic.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderItemResponseDto create(String authorizedUserEmail, Long orderId, OrderItemCreateRequestDto createRequestDto) {
        OrderItem orderItem = orderItemMapper.toOrderItem(authorizedUserEmail, orderId, createRequestDto); // leave it as it is: if add specific logic to other fields
        return orderItemMapper.toOrderItemResponseDto(orderItemRepository.save(orderItem));
    }

    @Override
    @Transactional
    public ListResponseDto<OrderItemResponseDto> findAll(Long orderId, Pageable pageable) {
        Page<OrderItem> orderPage = orderItemRepository.findAllByOrderId(orderId, pageable);
        return new ListResponseDto<OrderItemResponseDto>()
                .setList(orderPage.stream()
                        .map(orderItemMapper::toOrderItemResponseDto)
                        .toList())
                .setTotalRows(orderPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderItemResponseDto findByOrderAndProductIds(Long orderId, Long productId) {
        return orderItemRepository.findByOrderIdAndProductId(orderId, productId)
                .map(orderItemMapper::toOrderItemResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Order item not found by order id '%d' and product id '%d'", orderId, productId)));
    }

    @Override
    @Transactional
    public void deleteById(Long orderId, Long productId) {
        orderItemRepository.deleteByOrderIdAndProductId(orderId, productId);
    }

    @Override
    @Transactional
    public OrderItemResponseDto updateById(Long orderId, Long productId, OrderItemUpdateRequestDto requestDto) {
        OrderItem orderItem = orderItemRepository.findByOrderIdAndProductId(orderId, productId).orElseThrow(
                () -> new EntityNotFoundException(String
                        .format("Order item with order id '%d' and product id '%d' not found", orderId, productId))
        );
        orderItemMapper.updateOrderItem(requestDto, orderItem);
        orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemResponseDto(orderItem);
    }

    @Override
    public List<Sort.Order> getSortOrder() {
        return ID_SORT_ORDER;
    }
}
