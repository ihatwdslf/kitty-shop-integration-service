package kittyshop.integration.service.api.logic.order.dto;

import kittyshop.integration.service.api.logic.reference.entity.PaymentMethod;
import kittyshop.integration.service.api.logic.reference.entity.Status;
import kittyshop.integration.service.api.logic.user.dto.UserOnlyFetchResponseDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Accessors(chain = true)
public class OrderResponseDto {
    private Long id;
    private UserOnlyFetchResponseDto user;
    private Status status;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private PaymentMethod paymentMethod;
    private Set<OrderItemResponseDto> orderItems;
}
