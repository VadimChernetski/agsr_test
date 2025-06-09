package by.agsr.monitor.service.auth.impl;


import by.agsr.common.Role;
import by.agsr.monitor.Tuple;
import by.agsr.monitor.dao.entity.RefreshToken;
import by.agsr.monitor.dao.entity.User;
import by.agsr.monitor.dao.interfaces.RefreshTokenService;
import by.agsr.monitor.dto.TokenDto;
import by.agsr.monitor.exception.ExpiredTokenException;
import by.agsr.monitor.exception.InvalidTokenException;
import by.agsr.monitor.service.auth.interfaces.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static by.agsr.monitor.Constants.*;


@Service
@Getter
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final RefreshTokenService refreshTokenService;

    @Value("${authentication.token.secretKey}")
    private String secretKey;

    @Override
    public TokenDto generateToken(User user, Role role) {
        UUID tokenId = UUID.randomUUID();
        Date now = new Date();
        Date refreshTokenExpiration = new Date(now.getTime() + 24 * 60 * 60 * 1000);
        Date accessTokenExpiration = new Date(now.getTime() + 60 * 60 * 1000);
        String refreshToken = Jwts.builder()
          .subject(user.getNickname())
          .claim(ACCESS_TOKEN_ID, tokenId.toString())
          .signWith(getSigningKey())
          .issuedAt(now)
          .expiration(refreshTokenExpiration)
          .compact();

        RefreshToken savedRefreshToken = refreshTokenService.save(refreshToken, user);

        String accessToken = Jwts.builder()
          .subject(user.getNickname())
          .claim(ROLE_CLAIM_KEY, role)
          .claim(ACCESS_TOKEN_ID, tokenId.toString())
          .claim(REFRESH_TOKEN_ID, savedRefreshToken.getId().toString())
          .signWith(getSigningKey())
          .issuedAt(now)
          .expiration(accessTokenExpiration)
          .compact();
        return TokenDto.builder()
          .accessToken(accessToken)
          .refreshToken(refreshToken)
          .build();
    }

    @Override
    public TokenDto refreshToken(User user, String accessToken, String refreshToken) {
        Claims expiredClaims = extractClaimsFromExpiredToken(accessToken);
        Claims refreshClaims = extractClaimsFromToken(refreshToken);
        Long refreshTokenId = getRefreshTokenId(expiredClaims);
        Optional<RefreshToken> refreshTokenById = refreshTokenService.findRefreshTokenById(refreshTokenId);
        if (refreshTokenById.isEmpty() || !Objects.equals(refreshToken, refreshTokenById.get().getToken())) {
            throw new InvalidTokenException("token not found or not correct");
        }
        String accessTokenIdFromAccessToken = getAccessTokenId(expiredClaims);
        String accessTokenIdFromRefreshToken = getAccessTokenId(refreshClaims);
        if (!Objects.equals(accessTokenIdFromAccessToken, accessTokenIdFromRefreshToken)) {
            throw new InvalidTokenException("invalid reference id");
        }
        Role role = getRoleFromExpiredToken(accessToken);
        return generateToken(user, role);
    }

    @Override
    public Tuple<String, Role> getNicknameAndRole(String token) {
        Claims claims = extractClaimsFromToken(token);
        String roleString = claims.get(ROLE_CLAIM_KEY, String.class);
        if (Objects.isNull(roleString)) {
            throw new InvalidTokenException("invalid token");
        }
        String nickname = claims.getSubject();
        if (Objects.isNull(nickname)) {
            throw new InvalidTokenException("invalid token");
        }
        return Tuple.of(nickname, Role.valueOf(roleString));
    }


    @Override
    public String getNicknameFromExpiredToken(String token) {
        Claims claims = extractClaimsFromExpiredToken(token);
        return claims.getSubject();
    }

    public Role getRoleFromExpiredToken(String token) {
        Claims claims = extractClaimsFromExpiredToken(token);
        String roleString = claims.get(ROLE_CLAIM_KEY, String.class);
        return roleString == null ? null : Role.valueOf(roleString);
    }

    private String getAccessTokenId(Claims claims) {
        return claims.get(ACCESS_TOKEN_ID, String.class);
    }

    private Long getRefreshTokenId(Claims claims) {
        return Long.valueOf(claims.get(REFRESH_TOKEN_ID, String.class));
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Encoders.BASE64.encode(secretKey.getBytes()).getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractClaimsFromExpiredToken(String token) {
        return Jwts.parser()
          .verifyWith(getSigningKey())
          .clockSkewSeconds(Long.MAX_VALUE / 1000).build()
          .parseSignedClaims(token)
          .getPayload();
    }

    private Claims extractClaimsFromToken(String token) {
        try {
            return Jwts.parser()
              .verifyWith(getSigningKey())
              .build()
              .parseSignedClaims(token)
              .getPayload();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("Expired token");
        } catch (Exception e) {
            throw new InvalidTokenException("Invalid token");
        }
    }
}
