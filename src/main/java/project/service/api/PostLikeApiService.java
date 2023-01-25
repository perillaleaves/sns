package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.PostLike;
import project.domain.post.Post;
import project.domain.user.User;
import project.exception.PostNotFoundException;
import project.repository.PostLikeRepository;
import project.repository.PostRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeApiService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void flipLike(Long postId, HttpServletRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        User user = (User) request.getAttribute("userId");
        Optional<PostLike> findLike = postLikeRepository.findByPostAndUser(post, user);

        if (findLike.isEmpty()) {
            PostLike postLike = PostLike.addLike(post, request);
            postLikeRepository.save(postLike);
        } else {
            post.removePostLikeSize(post.getPostLikeSize());
            postLikeRepository.deleteById(findLike.get().getId());
        }
    }

}