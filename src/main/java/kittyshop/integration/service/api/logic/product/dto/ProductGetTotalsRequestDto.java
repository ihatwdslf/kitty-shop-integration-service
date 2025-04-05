package kittyshop.integration.service.api.logic.product.dto;

import kittyshop.integration.service.api.logic.order.dto.OrderItemCreateRequestDto;
import lombok.Data;

import java.util.Set;

@Data
public class ProductGetTotalsRequestDto {
    private Set<OrderItemCreateRequestDto> cartItems;
}
