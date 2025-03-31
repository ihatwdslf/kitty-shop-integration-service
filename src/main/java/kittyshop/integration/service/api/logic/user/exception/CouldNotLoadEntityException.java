package kittyshop.integration.service.api.logic.user.exception;

import kittyshop.integration.service.api.logic.common.exception.CustomException;
import kittyshop.integration.service.api.logic.common.exception.response.ApiErrorCodes;
import lombok.Getter;
import lombok.experimental.StandardException;

@Getter
@StandardException
public class CouldNotLoadEntityException extends CustomException {

    private String trySolution;

    public CouldNotLoadEntityException(String message, ApiErrorCodes code, String details, String trySolution) {
        super(message, code, details);
        this.trySolution = trySolution;
    }
}
