package kittyshop.integration.service.api.dto;

public record UserLoginResponseDto(String token, UserResponseDto user) {
}
