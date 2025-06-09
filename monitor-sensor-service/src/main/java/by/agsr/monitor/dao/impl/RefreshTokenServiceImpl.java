package by.agsr.monitor.dao.impl;

import by.agsr.monitor.dao.entity.RefreshToken;
import by.agsr.monitor.dao.entity.User;
import by.agsr.monitor.dao.interfaces.RefreshTokenService;
import by.agsr.monitor.dao.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public RefreshToken save(String refreshToken, User user) {

        RefreshToken refreshTokenEntity = RefreshToken.builder()
          .token(refreshToken)
          .user(user)
          .build();
        return refreshTokenRepository.save(refreshTokenEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findRefreshTokenById(Long refreshTokenId) {
        return refreshTokenRepository.findById(refreshTokenId);
    }

}

