package kittyshop.integration.service.api.logic.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ProductUpdateRequestDto {
    private String key;
    private String name;
    private String description;
    private BigDecimal price;
    
    @Min(0)
    @Max(100)
    private Integer discount;
    
    private String imageUrl;
    private Integer stockQuantity;
    private String stockKeepingUnit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long brandId;
    private Set<String> categoryKeys;
}
