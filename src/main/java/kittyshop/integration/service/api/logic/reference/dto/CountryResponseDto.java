package kittyshop.integration.service.api.logic.reference.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CountryResponseDto {
    private Long id;
    private String name;
    private String code;
}
