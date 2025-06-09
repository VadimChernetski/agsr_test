package by.agsr.monitor.service.auth.impl;

import by.agsr.common.Role;
import by.agsr.monitor.dao.entity.User;
import by.agsr.monitor.dao.interfaces.UserService;
import by.agsr.monitor.dto.TokenDto;
import by.agsr.monitor.exception.UserNotFoundException;
import by.agsr.monitor.service.auth.interfaces.AuthenticationService;
import by.agsr.monitor.service.auth.interfaces.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public TokenDto authenticate(String nickname, String password) {
        Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(nickname, password));
        ArrayList<? extends GrantedAuthority> grantedAuthorities = new ArrayList<>(authentication.getAuthorities());
        User user = userService.findUserByNickname(nickname)
          .orElseThrow(() -> new UserNotFoundException("user with nickname " + nickname + " not found"));
        return jwtService.generateToken(user, Role.valueOf(grantedAuthorities.getFirst().getAuthority()));
    }
}
