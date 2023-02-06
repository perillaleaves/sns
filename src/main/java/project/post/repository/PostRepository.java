package project.post.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import project.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"postImages", "user"})
    Optional<Post> findById(Long id);

    boolean existsPostByIdAndUserId(Long id, Long userId);

    @EntityGraph(attributePaths = {"postImages"})
    List<Post> findByUserId(Long userId);

//    @EntityGraph(attributePaths = {"postImages"})
//    Slice<Post> findByUserId(Long userId, Pageable pageable);

}