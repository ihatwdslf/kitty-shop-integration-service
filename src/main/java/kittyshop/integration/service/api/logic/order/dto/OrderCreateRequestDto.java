package kittyshop.integration.service.api.logic.order.dto;

import lombok.Data;

@Data
public class OrderCreateRequestDto {
    private String shippingAddress;
    private String paymentMethod;
}
