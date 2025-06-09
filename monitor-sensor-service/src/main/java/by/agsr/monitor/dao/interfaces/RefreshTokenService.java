package by.agsr.monitor.dao.interfaces;

import by.agsr.monitor.dao.entity.RefreshToken;
import by.agsr.monitor.dao.entity.User;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken save(String refreshToken, User user);

    Optional<RefreshToken> findRefreshTokenById(Long refreshTokenId);


}
