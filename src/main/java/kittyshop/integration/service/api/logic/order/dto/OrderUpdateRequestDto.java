package kittyshop.integration.service.api.logic.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderUpdateRequestDto {
    private Long userId; // if user wants to transfer order to another user

    private String statusKey;

    private String shippingAddress;

    private String paymentMethodKey;

    private String deliveryOptionKey;
}
