package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.PostLike;
import project.domain.post.Post;
import project.domain.user.UserToken;
import project.exception.APIError;
import project.exception.AccessTokenNotFoundException;
import project.exception.PostLikeNotFoundException;
import project.exception.PostNotFoundException;
import project.repository.PostLikeRepository;
import project.repository.PostRepository;
import project.repository.TokenRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeApiService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public void addLike(Long postId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        if (postLikeRepository.existsPostLikeByPostIdAndUserId(post.getId(), accessToken.getUser().getId())) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }

        PostLike postLike = PostLike.addLike(post, accessToken.getUser());
        post.addPostLikeSize(post.getPostLikeSize());
        postLikeRepository.save(postLike);
    }

    public void removeLike(Long postId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        PostLike postLike = postLikeRepository.findByPostIdAndUserId(postId, accessToken.getUser().getId())
                .orElseThrow(PostLikeNotFoundException::new);

        postLike.getPost().removePostLikeSize(postLike.getPost().getPostLikeSize());
        postLikeRepository.delete(postLike);
    }

}