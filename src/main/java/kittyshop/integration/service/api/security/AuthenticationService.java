package kittyshop.integration.service.api.security;

import kittyshop.integration.service.api.dto.UserLoginRequestDto;
import kittyshop.integration.service.api.dto.UserLoginResponseDto;
import kittyshop.integration.service.api.dto.UserResponseDto;
import kittyshop.integration.service.api.mapper.UserMapperImpl;
import kittyshop.integration.service.api.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final UserMapperImpl userMapperImpl;

    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtUtil.generateToken(authentication.getName());

        UserResponseDto userResponseDto = userMapperImpl.toUserResponseDto(
                userService.findByEmail(request.getEmail()).orElseThrow()
        );
        return new UserLoginResponseDto(token, userResponseDto);
    }

    public boolean isTokenValid(String token) {
        return jwtUtil.isValidToken(token);
    }

    public UserResponseDto getUserFromToken(String token) {
        String email = jwtUtil.getEmail(token);
        return userMapperImpl.toUserResponseDto(
                userService.findByEmail(email).orElseThrow()
        );
    }
}
