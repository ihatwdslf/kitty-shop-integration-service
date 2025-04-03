package kittyshop.integration.service.api.logic.product.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductRequestDto {
    private Set<String> categoryKeys;
    private Long brandId;
}
