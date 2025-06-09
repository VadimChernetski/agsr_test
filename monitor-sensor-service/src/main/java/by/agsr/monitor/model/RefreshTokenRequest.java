package by.agsr.monitor.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshTokenRequest {

    @NotNull(message = "access token should not be null")
    @NotEmpty(message = "access token should not be empty")
    private String accessToken;

    @NotNull(message = "refresh token should not be null")
    @NotEmpty(message = "refresh token should not be empty")
    private String refreshToken;
}
