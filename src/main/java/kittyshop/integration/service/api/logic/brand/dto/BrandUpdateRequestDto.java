package kittyshop.integration.service.api.logic.brand.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandUpdateRequestDto {
    private String name;
    private String description;
    private String website;
    private String countryCode;
}
