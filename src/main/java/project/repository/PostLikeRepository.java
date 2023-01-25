package project.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.domain.post.PostLike;
import project.domain.post.Post;
import project.domain.user.User;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    @EntityGraph(attributePaths = {"post", "user"})
    Optional<PostLike> findByPostAndUser(Post post, User user);

    boolean existsPostLikeByUser(User user);

}