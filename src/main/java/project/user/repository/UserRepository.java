package project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean isEmailExists(String email);

    boolean isNickNameExists(String nickName);

    Optional<User> findByEmail(String email);

}