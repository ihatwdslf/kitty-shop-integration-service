package kittyshop.integration.service.api.logic.user.exception.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kittyshop.integration.service.api.config.ControllerRoutes;
import kittyshop.integration.service.api.logic.common.exception.response.ApiError;
import kittyshop.integration.service.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static kittyshop.integration.service.api.logic.common.exception.response.ApiErrorCodes.VALIDATION_ERROR;

@Component
@RequiredArgsConstructor
public class AuthorizationCheckFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if (isAuthorizationMissingRequired(request)) {
            if (jwtUtil.isAlreadyAuthorized(request)) {
                response.setStatus(HttpStatus.OK.value());
                response.getWriter().write(objectMapper.writeValueAsString(new ApiError(
                        VALIDATION_ERROR.getErrorCode(),
                        "Unauthorized user required\nUser is already authorized"))
                );
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isAuthorizationMissingRequired(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals(ControllerRoutes.AUTH_LOGIN) || path.equals(ControllerRoutes.AUTH_REGISTRATION);
    }
}
