package project.reComment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.reComment.domain.ReComment;

public interface ReCommentRepository extends JpaRepository<ReComment, Long> {

    Boolean existsReCommentByIdAndUserId(Long id, Long userId);

}