package project.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.token.domain.UserToken;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<UserToken, Long> {

    void deleteByAccessToken(String token);

    Optional<UserToken> findByAccessToken(String token);

}