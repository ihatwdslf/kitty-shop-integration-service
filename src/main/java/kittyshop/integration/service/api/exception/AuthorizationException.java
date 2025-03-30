package kittyshop.integration.service.api.exception;

import kittyshop.integration.service.api.exception.response.ApiErrorCodes;

public abstract class AuthorizationException extends CustomException {

    protected AuthorizationException(String message, String cause) {
        super(message, ApiErrorCodes.AUTHORIZATION_ERROR, cause);
    }

    protected AuthorizationException(String message) {
        super(message, ApiErrorCodes.AUTHORIZATION_ERROR, "");
    }
}