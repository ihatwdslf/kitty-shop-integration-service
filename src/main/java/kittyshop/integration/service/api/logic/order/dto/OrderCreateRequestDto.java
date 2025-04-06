package kittyshop.integration.service.api.logic.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderCreateRequestDto {
    
    @NotBlank
    private String shippingAddress;
    
    @NotBlank
    private String paymentMethodKey;
}
