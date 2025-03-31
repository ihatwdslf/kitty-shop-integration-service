package kittyshop.integration.service.api.logic.order.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderItemResponseDto {
    private Long productId;
    private Integer quantity;
}
