package kittyshop.integration.service.api.logic.product.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductGetTotalsResponseDto {
    private Double totalWithoutDiscount;
    private Double totalWithDiscount;
    private Double discountDifference;
}
