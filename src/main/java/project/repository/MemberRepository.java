package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}