package project.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.domain.post.Like;
import project.domain.post.Post;
import project.domain.user.User;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @EntityGraph(attributePaths = { "post", "user" })
    Optional<Like> findByPostAndUser(Post post, User user);

}
