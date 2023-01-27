package project.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.domain.post.PostLike;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    @EntityGraph(attributePaths = {"post", "user"})
    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);

    boolean existsPostLikeByPostIdAndUserId(Long postId, Long userId);

}