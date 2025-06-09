package by.agsr.monitor.controller;

import by.agsr.monitor.dao.entity.User;
import by.agsr.monitor.dao.interfaces.UserService;
import by.agsr.monitor.dto.TokenDto;
import by.agsr.monitor.exception.UserNotFoundException;
import by.agsr.monitor.model.AuthResponse;
import by.agsr.monitor.model.RefreshTokenRequest;
import by.agsr.monitor.service.auth.interfaces.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth/refresh-token")
@RestController
@RequiredArgsConstructor
@Tag(name = "refresh token controller")
public class RefreshTokenController {

    private final JwtService jwtService;
    private final UserService userService;

    @Operation(
      description = "refresh jwt token"
    )
    @PostMapping
    public AuthResponse refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        String nickname = jwtService.getNicknameFromExpiredToken(refreshTokenRequest.getAccessToken());
        User user = userService.findUserByNickname(nickname)
          .orElseThrow(() -> new UserNotFoundException("User not found"));
        TokenDto tokenDto =
          jwtService.refreshToken(user, refreshTokenRequest.getAccessToken(), refreshTokenRequest.getRefreshToken());
        return AuthResponse.of(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }

}
