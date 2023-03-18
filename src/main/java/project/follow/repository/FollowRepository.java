package project.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.follow.domain.Follow;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    Boolean isFollowExistsByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

}