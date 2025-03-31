package kittyshop.integration.service.api.logic.user.exception;

import kittyshop.integration.service.api.logic.common.exception.CustomException;
import kittyshop.integration.service.api.logic.common.exception.response.ApiErrorCodes;

public abstract class AuthorizationException extends CustomException {

    protected AuthorizationException(String message, String cause) {
        super(message, ApiErrorCodes.AUTHORIZATION_ERROR, cause);
    }

    protected AuthorizationException(String message) {
        super(message, ApiErrorCodes.AUTHORIZATION_ERROR, "");
    }
}