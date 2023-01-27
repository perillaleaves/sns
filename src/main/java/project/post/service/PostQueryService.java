package project.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.post.response.PostDetailResponse;
import project.post.response.PostImagesResponse;
import project.token.domain.UserToken;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.token.repository.TokenRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostQueryService {

    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public PostDetailResponse findPostDetail(Long postId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        boolean isLike = post.getPostLikes().stream().anyMatch(like -> like.isLikeOf(accessToken.getUser()));

        return new PostDetailResponse(post.getId(), post.getUser().getId(), post.getUser().getUserProfileImage(), post.getUser().getNickName(),
                post.getPostImages().stream().map(postImage -> new PostImagesResponse(postImage.getId(), postImage.getImageUrl())).collect(Collectors.toList()),
                post.getContent(), isLike, post.getPostLikeSize(), post.getCommentSize(), post.getUpdatedAt());
    }

}