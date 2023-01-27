package project.postLike.reposiotry;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.postLike.domain.PostLike;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    @EntityGraph(attributePaths = {"post", "user"})
    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);

    boolean existsPostLikeByPostIdAndUserId(Long postId, Long userId);

}