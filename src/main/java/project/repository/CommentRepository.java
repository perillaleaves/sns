package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.domain.post.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}