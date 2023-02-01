package project.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.comment.repository.CommentRepository;
import project.comment.response.CommentResponse;
import project.comment.response.PostCommentResponse;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentQueryService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TokenRepository tokenRepository;

    public CommentQueryService(PostRepository postRepository, CommentRepository commentRepository, TokenRepository tokenRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.tokenRepository = tokenRepository;
    }

    public PostCommentResponse findCommentsByPost(Long postId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        return new PostCommentResponse(post.getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(), post.getContent(), post.getUpdatedAt(),
                post.getComments().stream()
                        .map(comment -> new CommentResponse(comment.getId(),
                                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + comment.getUser().getUserProfileImage().getUserProfileImageURL(),
                                comment.getUser().getNickName(),
                                comment.getContent(), commentRepository.existsCommentByIdAndUserId(comment.getId(), accessToken.getUser().getId()),
                                comment.getUpdatedAt())).collect(Collectors.toSet()));
    }

}