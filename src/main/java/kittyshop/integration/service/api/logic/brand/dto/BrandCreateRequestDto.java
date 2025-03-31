package kittyshop.integration.service.api.logic.brand.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandCreateRequestDto {

    @Size(min = 3, max = 64)
    private String name;

    @Size(min = 12, max = 255)
    private String description;

    @Size(min = 3, max = 64)
    private String website;
    
    private String countryCode;
}
