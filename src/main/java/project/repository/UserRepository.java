package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import project.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsMemberByEmail(String email);
    boolean existsMemberByNickName(String nickName);

//    @Query("select m.email, m.password from Member m where m.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

}