package kittyshop.integration.service.api.logic.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderUpdateRequestDto {
    private Long userId; // if user wants to transfer order to another user
    private String status;
    
    @NotBlank
    private String shippingAddress;
    
    @NotBlank
    private String paymentMethodKey;
}
