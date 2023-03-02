package project.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.post.domain.PostImage;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    List<PostImage> findByPostId(Long postId);

}