package kittyshop.integration.service.api.logic.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    // min: 0xx(xxx)(xx)-(xx), max: 380xx(xxx)(xx)-(xx)
    @Min(value = 1000000000L)
    @Max(value = 999999999999L)
    private Long phone;

    private String address;
}
