package kittyshop.integration.service.api.logic.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequestDto {

    @Size(min = 2, max = 40)
    private String firstName;

    @Size(min = 2, max = 40)
    private String lastName;

    @Pattern(regexp = "^\\+\\d{3}(?:\\s?\\d{2}\\s?\\d{3}\\s?\\d{4,5})?$",
            message = "must match the phone number format")
    private String phone;

    private String address;
}
