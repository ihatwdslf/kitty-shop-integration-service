package kittyshop.integration.service.api.exception.response;

import lombok.experimental.StandardException;

@StandardException
public class UserAlreadyAuthorizedException extends RuntimeException {
    public UserAlreadyAuthorizedException() {
        super("User is already authorized");
    }
}
