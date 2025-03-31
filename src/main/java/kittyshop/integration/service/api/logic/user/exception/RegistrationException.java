package kittyshop.integration.service.api.logic.user.exception;

import kittyshop.integration.service.api.logic.common.exception.CustomException;

public class RegistrationException extends CustomException {
    
    public RegistrationException(String message) {
        super(message);
    }
}
