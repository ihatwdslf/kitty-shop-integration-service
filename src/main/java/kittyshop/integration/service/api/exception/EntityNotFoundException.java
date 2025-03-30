package kittyshop.integration.service.api.exception;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
