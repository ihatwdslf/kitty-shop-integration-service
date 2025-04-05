package kittyshop.integration.service.api.logic.common.exception;

import lombok.experimental.StandardException;

@StandardException
public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
