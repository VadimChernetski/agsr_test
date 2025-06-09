package by.agsr.monitor.service.auth.interfaces;

import by.agsr.monitor.dto.TokenDto;

public interface AuthenticationService {

    TokenDto authenticate(String nickname, String password);

}
