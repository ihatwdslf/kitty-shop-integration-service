package kittyshop.integration.service.api.logic.order.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.entity.Order;
import kittyshop.integration.service.api.logic.order.mapper.OrderMapper;
import kittyshop.integration.service.api.logic.order.repository.OrderRepository;
import kittyshop.integration.service.api.logic.order.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto create(String authorizedUserEmail, OrderCreateRequestDto createRequestDto) {
        Order order = orderMapper.toOrder(authorizedUserEmail, createRequestDto); // leave it as it is: if add specific logic to other fields
        return orderMapper.toOrderResponseDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public ListResponseDto<OrderResponseDto> findAll(String authorizedUserEmail, Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAllByUserEmail(authorizedUserEmail, pageable);
        return new ListResponseDto<OrderResponseDto>()
                .setList(orderPage.stream()
                        .map(orderMapper::toOrderResponseDto)
                        .toList())
                .setTotalRows(orderPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderResponseDto findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponseDto updateById(Long id, OrderUpdateRequestDto requestDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Order with id '%s' not found", id))
        );
        orderMapper.updateOrder(requestDto, order);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDto(order);
    }

    @Override
    public List<Sort.Order> getSortOrder() {
        return ID_SORT_ORDER;
    }
}
