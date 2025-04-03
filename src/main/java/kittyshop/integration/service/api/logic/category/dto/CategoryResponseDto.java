package kittyshop.integration.service.api.logic.category.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryResponseDto {
    private Long id;
    private String key;
    private String name;
    private String description;
    private String icon;
    private Long parentId;
    private Boolean isQuicklyAccessible;
    private Boolean isRemovable;
}
