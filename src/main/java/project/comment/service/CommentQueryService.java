package project.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.comment.domain.Comment;
import project.comment.repository.CommentRepository;
import project.comment.response.CommentResponse;
import project.comment.response.PostAndCommentsResponse;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.post.response.PostSummaryResponse;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;
import project.user.response.UserSimpleResponse;

import java.util.List;
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

    public PostAndCommentsResponse findCommentsByPost(Long postId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        UserSimpleResponse userSimpleResponse = new UserSimpleResponse(
                accessToken.getUser().getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + accessToken.getUser().getUserProfileImage().getUserProfileImageURL(),
                accessToken.getUser().getNickName());

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        PostSummaryResponse postSummaryResponse = new PostSummaryResponse(post.getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(), post.getContent(), post.getUpdatedAt());

        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<CommentResponse> commentResponses = comments.stream()
                .map(c -> new CommentResponse(c.getId(),
                        "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + c.getUser().getUserProfileImage().getUserProfileImageURL(),
                        c.getUser().getNickName(),
                        c.getContent(),
                        commentRepository.existsCommentByIdAndUserId(c.getId(), accessToken.getUser().getId()),
                        c.getUpdatedAt())).collect(Collectors.toList());

        return new PostAndCommentsResponse(userSimpleResponse, postSummaryResponse, commentResponses);
    }

}