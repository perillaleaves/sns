package project.follow.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.follow.domain.Follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    Boolean existsFollowByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    List<Follow> findByFromUserId(Long froUserId);

}