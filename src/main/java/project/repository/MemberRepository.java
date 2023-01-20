package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import project.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberByEmail(String email);
    boolean existsMemberByNickName(String nickName);

//    @Query("select m.email, m.password from Member m where m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email);

}