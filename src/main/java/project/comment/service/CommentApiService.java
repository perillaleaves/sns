package project.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.comment.repository.CommentRepository;
import project.comment.request.CommentRequest;
import project.comment.domain.Comment;
import project.post.domain.Post;
import project.token.domain.UserToken;
import project.common.exception.APIError;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.CommentNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.post.repository.PostRepository;
import project.token.repository.TokenRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentApiService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public void create(Long postId, CommentRequest request, String token) {
        validation(request);
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(accessToken.getUser())
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    public void update(Long commentId, CommentRequest request, String token) {
        validation(request);
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        if (!comment.getUser().equals(accessToken.getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }
        comment.update(request.getContent());
    }

    public void delete(Long commentId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        if (!comment.getUser().equals(accessToken.getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }
        comment.getPost().removeCommentSize(comment.getPost().getCommentSize());
        commentRepository.delete(comment);
    }

    private static void validation(CommentRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }

}