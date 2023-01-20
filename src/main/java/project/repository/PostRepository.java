package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
