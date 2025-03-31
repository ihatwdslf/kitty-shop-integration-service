package kittyshop.integration.service.api.logic.user.service;

import kittyshop.integration.service.api.logic.common.dto.ListResponseDto;
import kittyshop.integration.service.api.logic.user.dto.UserRegistrationRequestDto;
import kittyshop.integration.service.api.logic.user.dto.UserResponseDto;
import kittyshop.integration.service.api.logic.user.dto.UserUpdateRequestDto;
import kittyshop.integration.service.api.logic.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    ListResponseDto<UserResponseDto> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsById(Long id);
    
    void deleteById(Long id);

    UserResponseDto updateById(Long id, UserUpdateRequestDto requestDto);
}
