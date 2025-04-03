package kittyshop.integration.service.api.logic.category.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryIdentifierAndNameResponseDto {
    private Long categoryId;
    private String categoryKey;
    private String categoryName;
}
