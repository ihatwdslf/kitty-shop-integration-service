package kittyshop.integration.service.api.logic.order.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.common.exception.NotEnoughQuantityException;
import kittyshop.integration.service.api.logic.order.dto.*;
import kittyshop.integration.service.api.logic.order.entity.OrderItem;
import kittyshop.integration.service.api.logic.order.mapper.OrderItemMapper;
import kittyshop.integration.service.api.logic.order.repository.OrderItemRepository;
import kittyshop.integration.service.api.logic.order.service.OrderItemService;
import kittyshop.integration.service.api.logic.product.dto.ProductResponseDto;
import kittyshop.integration.service.api.logic.product.dto.ProductUpdateRequestDto;
import kittyshop.integration.service.api.logic.product.service.ProductService;
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

    private final ProductService productService;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderItemResponseDto create(String authorizedUserEmail, Long orderId, OrderItemCreateRequestDto createRequestDto) {
        ProductResponseDto product = productService.findById(createRequestDto.getProductId());

        if (product == null) {
            throw new EntityNotFoundException("Product not found");
        }

        Integer availableStockQuantity = product.getStockQuantity();
        if (availableStockQuantity < createRequestDto.getQuantity()) {
            throw new NotEnoughQuantityException("Product stock not enough. Quantity: " + availableStockQuantity);
        }

        OrderItem orderItem = orderItemMapper.toOrderItem(authorizedUserEmail, orderId, createRequestDto); // leave it as it is: if add specific logic to other fields
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        productService.updateById(product.getId(),
                new ProductUpdateRequestDto()
                        .setStockQuantity(availableStockQuantity - createRequestDto.getQuantity())
        );
        return orderItemMapper.toOrderItemResponseDto(savedOrderItem);
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
