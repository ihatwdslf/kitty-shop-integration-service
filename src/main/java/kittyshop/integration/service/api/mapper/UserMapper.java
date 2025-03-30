package kittyshop.integration.service.api.mapper;

import kittyshop.integration.service.api.dto.UserRegistrationRequestDto;
import kittyshop.integration.service.api.dto.UserResponseDto;
import kittyshop.integration.service.api.dto.UserUpdateRequestDto;
import kittyshop.integration.service.api.entity.user.User;

public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);

    User toUser(UserRegistrationRequestDto registrationRequestDto);

    void updateUser(UserUpdateRequestDto userUpdateRequestDto, User user);
}
