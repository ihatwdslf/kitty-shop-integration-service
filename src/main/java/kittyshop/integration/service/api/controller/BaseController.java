package kittyshop.integration.service.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {
    private static final String NOT_IMPLEMENTED_MESSAGE = "This endpoint is not implemented yet";

    protected static ResponseEntity<?> notImplementedEndpoint() {
        return new ResponseEntity<>(NOT_IMPLEMENTED_MESSAGE, HttpStatus.NOT_IMPLEMENTED);
    }
}
