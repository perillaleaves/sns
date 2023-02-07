package project.post.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.post.domain.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"postImages", "user"})
    Optional<Post> findById(Long id);

    boolean existsPostByIdAndUserId(Long id, Long userId);

    Slice<Post> findByUserId(Long userId, Pageable pageable);

}