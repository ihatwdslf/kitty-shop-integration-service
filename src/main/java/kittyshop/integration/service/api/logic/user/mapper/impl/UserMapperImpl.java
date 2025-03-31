package kittyshop.integration.service.api.logic.user.mapper.impl;

import kittyshop.integration.service.api.logic.user.dto.*;
import kittyshop.integration.service.api.logic.user.entity.User;
import kittyshop.integration.service.api.logic.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setAddress(user.getAddress());

        return userResponseDto;
    }

    @Override
    public UserOnlyFetchResponseDto toUserOnlyFetchResponseDto(User user) {
        if (user == null) {
            return null;
        }

        UserOnlyFetchResponseDto userResponseDto = new UserOnlyFetchResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(new UserRoleOnlyFetchResponseDto()
                .setId(user.getRole().getId())
                .setName(user.getRole().getRole())
        );

        return userResponseDto;
    }

    @Override
    public User toUser(UserRegistrationRequestDto registrationRequestDto) {
        if (registrationRequestDto == null) {
            return null;
        }

        User user = new User();

        if (registrationRequestDto.getEmail() != null) {
            user.setEmail(registrationRequestDto.getEmail());
        }
        if (registrationRequestDto.getPassword() != null) {
            user.setPassword(registrationRequestDto.getPassword());
        }
        if (registrationRequestDto.getFirstName() != null) {
            user.setFirstName(registrationRequestDto.getFirstName());
        }
        if (registrationRequestDto.getLastName() != null) {
            user.setLastName(registrationRequestDto.getLastName());
        }

        return user;
    }

    @Override
    public void updateUser(UserUpdateRequestDto userUpdateRequestDto, User user) {
        if (userUpdateRequestDto == null || user == null) {
            return;
        }

        if (userUpdateRequestDto.getFirstName() != null) {
            user.setFirstName(userUpdateRequestDto.getFirstName());
        }

        if (userUpdateRequestDto.getLastName() != null) {
            user.setLastName(userUpdateRequestDto.getLastName());
        }

        if (userUpdateRequestDto.getPhone() != null) {
            user.setPhone(userUpdateRequestDto.getPhone());
        }

        if (userUpdateRequestDto.getAddress() != null) {
            user.setAddress(userUpdateRequestDto.getAddress());
        }
    }
}
