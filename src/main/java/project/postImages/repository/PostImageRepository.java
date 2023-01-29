package project.postImages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.postImages.domain.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
