package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Comment;
import project.domain.post.Post;
import project.domain.user.UserToken;
import project.exception.APIError;
import project.exception.AccessTokenNotFoundException;
import project.exception.CommentNotFoundException;
import project.exception.PostNotFoundException;
import project.repository.CommentRepository;
import project.repository.PostRepository;
import project.repository.TokenRepository;
import project.request.CommentRequest;

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
        Comment comment = Comment.create(post, request, accessToken.getUser());
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