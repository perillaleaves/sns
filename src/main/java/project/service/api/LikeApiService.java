package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Like;
import project.domain.post.Post;
import project.domain.user.User;
import project.domain.user.UserToken;
import project.repository.LikeRepository;
import project.repository.PostRepository;
import project.repository.TokenRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeApiService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public void flipLike(Long postId, HttpServletRequest request) {
        Post post = postRepository.findById(postId).orElse(null);
        User user = (User) request.getAttribute("userId");
        Optional<Like> findLike = likeRepository.findByPostAndUser(post, user);

        if (findLike.isEmpty()) {
            Like like = Like.addLike(post, request);
            likeRepository.save(like);
        } else {
            post.removePostLike(post.getLikeSize());
            likeRepository.deleteById(findLike.get().getId());
        }
    }

}
