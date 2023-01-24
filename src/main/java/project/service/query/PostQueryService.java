package project.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Post;
import project.domain.user.UserToken;
import project.repository.PostRepository;
import project.repository.TokenRepository;
import project.response.PostDetailResponse;
import project.response.PostImagesResponse;

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
        UserToken accessToken = tokenRepository.findByAccessToken(token).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);
        boolean isLike = post.getLikes().stream().anyMatch(like -> like.isLikeOf(accessToken.getUser()));

        return new PostDetailResponse(post.getId(), post.getUser().getId(), post.getUser().getProfileImage(), post.getUser().getNickName(),
                post.getPostImages().stream().map(postImage -> new PostImagesResponse(postImage.getId(), postImage.getImageUrl())).collect(Collectors.toList()),
                post.getContent(), isLike, post.getLikeSize(), post.getCommentSize(), post.getUpdatedAt());
    }

}