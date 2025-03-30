package kittyshop.integration.service.api.logic.user.mapper;

import kittyshop.integration.service.api.logic.user.dto.UserRegistrationRequestDto;
import kittyshop.integration.service.api.logic.user.dto.UserResponseDto;
import kittyshop.integration.service.api.logic.user.dto.UserUpdateRequestDto;
import kittyshop.integration.service.api.logic.user.entity.User;

public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);

    User toUser(UserRegistrationRequestDto registrationRequestDto);

    void updateUser(UserUpdateRequestDto userUpdateRequestDto, User user);
}
