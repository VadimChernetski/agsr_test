package by.agsr.monitor.dao.repository;

import by.agsr.monitor.dao.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  @Modifying
  @Query(nativeQuery = true, value = "INSERT INTO agsr.refresh_token (user_id, token) VALUES (?1, ?2);")
  Long saveToken(Long userId, String token);

}
