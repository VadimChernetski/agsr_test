package by.agsr.monitor.controller;

import by.agsr.monitor.dto.TokenDto;
import by.agsr.monitor.model.AuthRequest;
import by.agsr.monitor.model.AuthResponse;
import by.agsr.monitor.service.auth.interfaces.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth/sign-in")
@RestController
@RequiredArgsConstructor
@Tag(name = "sign in controller")
public class SignInController {

    private final AuthenticationService authenticationService;

    @Operation(
      description = "sign in"
    )
    @PostMapping
    public AuthResponse signIn(@RequestBody @Valid AuthRequest request) {
        TokenDto tokenDto = authenticationService.authenticate(request.getNickname(), request.getPassword());
        return AuthResponse.of(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }
}
