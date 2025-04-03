package kittyshop.integration.service.api.logic.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ProductCreateRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String stockKeepingUnit;
    private LocalDateTime createdAt;
    private Long brandId;
    private Set<String> categoryKeys;
}
