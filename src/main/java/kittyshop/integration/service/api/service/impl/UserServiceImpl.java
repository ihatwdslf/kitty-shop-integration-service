package kittyshop.integration.service.api.service.impl;

import kittyshop.integration.service.api.dto.ListResponseDto;
import kittyshop.integration.service.api.dto.UserRegistrationRequestDto;
import kittyshop.integration.service.api.dto.UserResponseDto;
import kittyshop.integration.service.api.dto.UserUpdateRequestDto;
import kittyshop.integration.service.api.entity.user.User;
import kittyshop.integration.service.api.exception.RegistrationException;
import kittyshop.integration.service.api.mapper.UserMapper;
import kittyshop.integration.service.api.repository.UserRepository;
import kittyshop.integration.service.api.repository.UserRoleRepository;
import kittyshop.integration.service.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException(String.format("User with this email '%s' already exists", requestDto.getEmail()));
        }

        User user = userMapper.toUser(requestDto)
                .setPassword(passwordEncoder.encode(requestDto.getPassword()))
                .setRole(roleRepository.findByRole("USER"));

        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public ListResponseDto<UserResponseDto> findAll(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return new ListResponseDto<UserResponseDto>()
                .setList(usersPage.stream()
                        .map(userMapper::toUserResponseDto)
                        .toList())
                .setTotalRows(usersPage.getTotalElements());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto updateById(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("User with id '%s' not found", id))
        );
        userMapper.updateUser(requestDto, user);
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }
}
