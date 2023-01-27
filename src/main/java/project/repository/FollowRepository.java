package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.domain.user.Follow;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

}