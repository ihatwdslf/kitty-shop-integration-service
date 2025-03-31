package kittyshop.integration.service.api.logic.common.exception.response;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class ExceptionResponse {

    public ResponseEntity<ApiError> buildResponseEntity(Integer code, String message, Throwable throwable) {
        return new ResponseEntity<>(new ApiError(code, 
                String.format("%s\n%s", message, throwable.getMessage())), 
                HttpStatus.OK
        );
    }
}
