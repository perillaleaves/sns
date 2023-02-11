package project.reCommentLike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.reCommentLike.domain.ReCommentLike;

public interface ReCommentLikeRepository extends JpaRepository<ReCommentLike, Long> {

    Boolean existsReCommentLikeByReCommentIdAndUserId(Long reCommentId, Long userId);

}