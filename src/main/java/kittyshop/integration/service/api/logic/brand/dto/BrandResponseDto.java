package kittyshop.integration.service.api.logic.brand.dto;

import kittyshop.integration.service.api.logic.reference.entity.Country;
import lombok.Data;

@Data
public class BrandResponseDto {
    private Long id;
    private String name;
    private String description;
    private String website;
    private Country country;
}
