package project.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.post.domain.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
