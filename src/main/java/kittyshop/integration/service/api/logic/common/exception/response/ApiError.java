package kittyshop.integration.service.api.logic.common.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError implements Serializable {
    
    public static final String PLEASE_CONTACT_SUPPORT_MESSAGE = "Please contact support.";
    
    @JsonProperty(value = "code")
    private Integer code;

    @JsonProperty(value = "message")
    private String message;
    
    @JsonProperty(value = "details")
    private String details;
    
    @JsonProperty(value = "try")
    private String trySolution;
    
    public ApiError(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }
    
    public ApiError(final Integer code, final String message, final String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
}
