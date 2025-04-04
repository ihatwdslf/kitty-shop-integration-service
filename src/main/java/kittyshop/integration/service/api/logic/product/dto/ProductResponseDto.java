package kittyshop.integration.service.api.logic.product.dto;

import kittyshop.integration.service.api.logic.brand.dto.BrandIdentifierAndNameResponseDto;
import kittyshop.integration.service.api.logic.category.dto.CategoryIdentifierAndNameResponseDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer discount;
    private String imageUrl;
    private Integer stockQuantity;
    private String stockKeepingUnit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BrandIdentifierAndNameResponseDto brand;
    private List<CategoryIdentifierAndNameResponseDto> categories;
}
