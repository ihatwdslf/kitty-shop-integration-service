package kittyshop.integration.service.api.logic.order.dto;

import lombok.Data;

@Data
public class OrderItemCreateRequestDto {
    private Long productId;
    private Integer quantity;
}
