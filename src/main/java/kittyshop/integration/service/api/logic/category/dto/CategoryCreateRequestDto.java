package kittyshop.integration.service.api.logic.category.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateRequestDto {

    @Size(min = 3, max = 64)
    private String name;

    @Size(min = 12, max = 255)
    private String description;
    
    private Long parentId;
}
