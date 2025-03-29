package kittyshop.integration.service.api.service;

import kittyshop.integration.service.api.dto.ListResponseDto;
import kittyshop.integration.service.api.dto.UserRegistrationRequestDto;
import kittyshop.integration.service.api.dto.UserResponseDto;
import kittyshop.integration.service.api.dto.UserUpdateRequestDto;
import kittyshop.integration.service.api.entity.user.User;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    ListResponseDto<UserResponseDto> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    void deleteById(Long id);

    UserResponseDto updateById(Long id, UserUpdateRequestDto requestDto);
}
