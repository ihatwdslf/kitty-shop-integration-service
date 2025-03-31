package kittyshop.integration.service.api.logic.user.exception;

import lombok.experimental.StandardException;

@StandardException
public class UserAlreadyAuthorizedException extends RuntimeException {
    public UserAlreadyAuthorizedException() {
        super("User is already authorized");
    }
}
