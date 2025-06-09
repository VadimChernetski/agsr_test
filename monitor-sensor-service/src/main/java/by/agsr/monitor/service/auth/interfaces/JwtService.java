package by.agsr.monitor.service.auth.interfaces;

import by.agsr.common.Role;
import by.agsr.monitor.Tuple;
import by.agsr.monitor.dao.entity.User;
import by.agsr.monitor.dto.TokenDto;

public interface JwtService {

  TokenDto generateToken(User user, Role role);

  TokenDto refreshToken(User user, String accessToken, String refreshToken);

  Tuple<String, Role> getNicknameAndRole(String token);

  String getNicknameFromExpiredToken(String token);


}
