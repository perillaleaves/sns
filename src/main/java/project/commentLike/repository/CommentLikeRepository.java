package project.commentLike.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.commentLike.domain.CommentLike;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Boolean existsCommentLikeByCommentIdAndUserId(Long commentId, Long userId);

    @EntityGraph(attributePaths = {"comment"})
    Optional<CommentLike> findByCommentIdAndUserId(Long CommentId, Long userId);

}