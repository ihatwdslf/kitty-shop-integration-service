package kittyshop.integration.service.api.logic.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreateRequestDto {

    @Size(min = 3, max = 64)
    private String key;

    @Size(min = 3, max = 64)
    private String name;

    @Size(min = 12, max = 255)
    private String description;
    
    private String icon;

    private Long parentId;
    
    private Boolean isQuicklyAccessible;
    
    private Boolean isRemovable;
}
