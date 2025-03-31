package kittyshop.integration.service.api.logic.order.mapper.impl;

import jakarta.persistence.EntityNotFoundException;
import kittyshop.integration.service.api.logic.order.dto.OrderCreateRequestDto;
import kittyshop.integration.service.api.logic.order.dto.OrderItemResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderResponseDto;
import kittyshop.integration.service.api.logic.order.dto.OrderUpdateRequestDto;
import kittyshop.integration.service.api.logic.order.entity.Order;
import kittyshop.integration.service.api.logic.order.mapper.OrderMapper;
import kittyshop.integration.service.api.logic.reference.entity.PaymentMethod;
import kittyshop.integration.service.api.logic.reference.entity.Status;
import kittyshop.integration.service.api.logic.reference.repository.PaymentMethodRepository;
import kittyshop.integration.service.api.logic.reference.repository.StatusRepository;
import kittyshop.integration.service.api.logic.user.entity.User;
import kittyshop.integration.service.api.logic.user.mapper.UserMapper;
import kittyshop.integration.service.api.logic.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

    private final UserMapper userMapper;
    private final UserService userService;
    private final StatusRepository statusRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public Order toOrder(String authorizedUserEmail, OrderCreateRequestDto createRequestDto) {
        if (createRequestDto == null) {
            return null;
        }

        Order order = new Order();
        order.setShippingAddress(createRequestDto.getShippingAddress());
        LocalDateTime orderDate = LocalDateTime.now();
        order.setOrderDate(orderDate);

        User user = userService.findByEmail(authorizedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("User does not found by id: " + authorizedUserEmail));
        order.setUser(user);

        String statusName = Status.Defaults.PENDING.getName();
        Status status = statusRepository.findByName(statusName)
                .orElseThrow(() -> new EntityNotFoundException("Status does not found by name: " + statusName));
        order.setStatus(status);

        PaymentMethod paymentMethod = paymentMethodRepository.findByName(createRequestDto.getPaymentMethod())
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod does not found by name: " + createRequestDto.getPaymentMethod()));
        order.setPaymentMethod(paymentMethod);

        return order;
    }

    @Override
    public Order toOrder(String authorizedUserEmail, OrderResponseDto responseDto) {
        if (responseDto == null) {
            return null;
        }

        Order order = new Order();
        order.setId(responseDto.getId());
        order.setShippingAddress(responseDto.getShippingAddress());
        order.setOrderDate(responseDto.getOrderDate());

        User user = userService.findByEmail(authorizedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("User does not found by id: " + authorizedUserEmail));
        order.setUser(user);

        Status status = statusRepository.findByName(responseDto.getStatus().getName())
                .orElseThrow(() -> new EntityNotFoundException("Status does not found by name: " + responseDto.getStatus().getName()));
        order.setStatus(status);

        PaymentMethod paymentMethod = paymentMethodRepository.findByName(responseDto.getPaymentMethod().getName())
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod does not found by name: " + responseDto.getPaymentMethod().getName()));
        order.setPaymentMethod(paymentMethod);

        return order;
    }

    @Override
    public OrderResponseDto toOrderResponseDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setStatus(order.getStatus());
        orderResponseDto.setPaymentMethod(order.getPaymentMethod());
        orderResponseDto.setUser(userMapper.toUserOnlyFetchResponseDto(order.getUser()));
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setShippingAddress(order.getShippingAddress());

        Set<OrderItemResponseDto> orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemResponseDto()
                        .setProductId(orderItem.getId().getProductId())
                        .setQuantity(orderItem.getQuantity()))
                .collect(Collectors.toSet());
        orderResponseDto.setOrderItems(orderItems);

        log.info("Mapped order response: {}", orderResponseDto);
        return orderResponseDto;
    }

    @Override
    public void updateOrder(OrderUpdateRequestDto updateRequestDto, Order order) {
        if (updateRequestDto == null || order == null) {
            return;
        }

        if (updateRequestDto.getShippingAddress() != null) {
            order.setShippingAddress(updateRequestDto.getShippingAddress());
        }

        LocalDateTime updateOrderDate = LocalDateTime.now();
        order.setOrderDate(updateOrderDate);

        if (updateRequestDto.getPaymentMethod() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findByName(updateRequestDto.getPaymentMethod())
                    .orElseThrow(() -> new EntityNotFoundException("PaymentMethod does not found by name: " + updateRequestDto.getPaymentMethod()));
            order.setPaymentMethod(paymentMethod);
        }

        if (updateRequestDto.getStatus() != null) {
            Status status = statusRepository.findByName(updateRequestDto.getStatus())
                    .orElseThrow(() -> new EntityNotFoundException("Status does not found by name: " + updateRequestDto.getStatus()));
            order.setStatus(status);
        }

        if (updateRequestDto.getUserId() != null) {
            User user = userService.findById(updateRequestDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User does not found by id: " + updateRequestDto.getUserId()));
            order.setUser(user);
        }
    }
}
