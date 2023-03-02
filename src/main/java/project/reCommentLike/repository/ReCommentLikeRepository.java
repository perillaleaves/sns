package project.reCommentLike.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.reCommentLike.domain.ReCommentLike;

import java.util.Optional;

public interface ReCommentLikeRepository extends JpaRepository<ReCommentLike, Long> {

    Boolean existsReCommentLikeByReCommentIdAndUserId(Long reCommentId, Long userId);

    @EntityGraph(attributePaths = {"reComment"})
    Optional<ReCommentLike> findByReCommentIdAndUserId(Long reCommentId, Long userId);

}