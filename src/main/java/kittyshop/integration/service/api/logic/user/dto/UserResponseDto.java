package kittyshop.integration.service.api.logic.user.dto;

import kittyshop.integration.service.api.logic.user.entity.UserRole;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String phone;
    private String address;
}
