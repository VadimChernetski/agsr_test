package by.agsr.monitor.controller;

import by.agsr.monitor.dao.entity.User;
import by.agsr.monitor.dao.interfaces.UserService;
import by.agsr.monitor.dto.TokenDto;
import by.agsr.monitor.model.AuthRequest;
import by.agsr.monitor.model.AuthResponse;
import by.agsr.monitor.service.auth.interfaces.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth/sign-up")
@RestController
@RequiredArgsConstructor
@Tag(name = "sign up controller")
public class SignUpController {

    private final JwtService jwtService;
    private final UserService userService;

    @Operation(
      description = "sign up (only for viewers)"
    )
    @PostMapping
    public AuthResponse signIn(@RequestBody @Valid AuthRequest request) {
        User user = userService.createUser(request.getNickname(), request.getPassword());
        TokenDto tokenDto = jwtService
          .generateToken(user, user.getRole().getRole());
        return AuthResponse.of(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }
}
