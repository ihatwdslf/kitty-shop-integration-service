package kittyshop.integration.service.api.logic.brand.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BrandIdentifierAndNameResponseDto {
    private Long brandId;
    private String brandName;
}
