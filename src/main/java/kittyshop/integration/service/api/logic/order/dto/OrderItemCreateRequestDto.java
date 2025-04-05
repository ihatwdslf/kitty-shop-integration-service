package kittyshop.integration.service.api.logic.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemCreateRequestDto {
    private Long productId;
    private Integer quantity;
}
