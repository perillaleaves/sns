package project.post.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.post.domain.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"postImages", "user", "comments"})
    Optional<Post> findById(Long id);

    boolean existsPostByIdAndUserId(Long id, Long userId);
}