package kittyshop.integration.service.api.logic.user.service.impl;

import kittyshop.integration.service.api.logic.common.exception.response.ApiError;
import kittyshop.integration.service.api.logic.common.exception.response.ApiErrorCodes;
import kittyshop.integration.service.api.logic.user.exception.CouldNotLoadEntityException;
import kittyshop.integration.service.api.logic.user.repository.UserRepository;
import kittyshop.integration.service.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private static final String USER_DELETED_ON_PREV_SERVER_INSTANCE_MESSAGE
            = ApiError.PLEASE_CONTACT_SUPPORT_MESSAGE + "Maybe your user were deleted or deactivated on previous server instance.";
    private static final String POSSIBLE_TRY_SOLUTION_MESSAGE
            = "Also you can try this fix: Open DevTools (F12) -> Application -> Cookies -> (our website) -> Delete '%s' cookie";

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new CouldNotLoadEntityException(String.format("Couldn't load user by email '%s'", email),
                        ApiErrorCodes.NOT_FOUND,
                        USER_DELETED_ON_PREV_SERVER_INSTANCE_MESSAGE,
                        POSSIBLE_TRY_SOLUTION_MESSAGE.formatted(jwtUtil.getAuthCookieName()))
        );
    }
}
