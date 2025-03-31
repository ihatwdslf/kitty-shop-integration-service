package kittyshop.integration.service.api.logic.user.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRoleOnlyFetchResponseDto {
    private Long id;
    private String name;
}
