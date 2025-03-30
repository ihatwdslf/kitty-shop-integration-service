package kittyshop.integration.service.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequestDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
