package kittyshop.integration.service.api.logic.user.dto;

public record UserLoginResponseDto(String token, UserResponseDto user) {
}
