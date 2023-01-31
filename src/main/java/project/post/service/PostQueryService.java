package project.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.post.response.PostDetailResponse;
import project.post.response.PostImagesResponse;
import project.postLike.reposiotry.PostLikeRepository;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;
    private final PostLikeRepository postLikeRepository;

    public PostQueryService(PostRepository postRepository, TokenRepository tokenRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.tokenRepository = tokenRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public PostDetailResponse findPostDetail(Long postId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        boolean isLike = postLikeRepository.existsPostLikeByPostIdAndUserId(post.getId(), accessToken.getUser().getId());
        boolean isPostEdit = postRepository.existsPostByIdAndUserId(post.getId(), accessToken.getUser().getId());

        return new PostDetailResponse(post.getId(), post.getUser().getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(),
                post.getPostImages().stream()
                        .map(postImage -> new PostImagesResponse(postImage.getId(),
                                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + postImage.getPostImageUrl())).collect(Collectors.toSet()),
                post.getContent(), isLike, post.getPostLikeSize(), post.getCommentSize(), isPostEdit, post.getUpdatedAt());
    }

}