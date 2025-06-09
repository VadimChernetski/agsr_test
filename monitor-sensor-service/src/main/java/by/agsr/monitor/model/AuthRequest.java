package by.agsr.monitor.model;

import by.agsr.monitor.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {

    @NotEmpty(message = "Nickname can not be empty")
    @NotNull(message = "Nickname can not be null")
    @Size(min = 5, max = 20, message = "The nickname must have from 5 to 20 characters.")
    private String nickname;

    @NotEmpty(message = "Password can not be empty")
    @NotNull(message = "Password can not be null")
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    private String password;

}
