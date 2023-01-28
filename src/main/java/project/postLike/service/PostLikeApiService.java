package project.postLike.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.APIError;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.PostLikeNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.postLike.domain.PostLike;
import project.postLike.reposiotry.PostLikeRepository;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;

@Service
@Transactional
public class PostLikeApiService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public PostLikeApiService(PostLikeRepository postLikeRepository, PostRepository postRepository, TokenRepository tokenRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
        this.tokenRepository = tokenRepository;
    }

    public void addLike(Long postId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        if (postLikeRepository.existsPostLikeByPostIdAndUserId(post.getId(), accessToken.getUser().getId())) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }

        PostLike postLike = PostLike.builder()
                .post(post)
                .user(accessToken.getUser())
                .build();
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