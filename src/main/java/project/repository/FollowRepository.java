package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.domain.user.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
