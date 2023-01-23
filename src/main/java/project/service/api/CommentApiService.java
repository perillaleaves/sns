package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Comment;
import project.domain.post.Post;
import project.domain.user.UserToken;
import project.exception.APIError;
import project.repository.CommentRepository;
import project.repository.PostRepository;
import project.repository.TokenRepository;
import project.request.CommentRequest;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentApiService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public void create(Long postId, CommentRequest request, HttpServletRequest httpServletRequest) {
        Post post = postRepository.findById(postId).orElse(null);
        Comment comment = Comment.create(post, request, httpServletRequest);
        commentRepository.save(comment);
    }

    public void update(Long commentId, CommentRequest request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token).orElse(null);
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (!comment.getUser().equals(accessToken.getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }
        comment.update(request.getContent());
    }

    public void delete(Long commentId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token).orElse(null);
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (!comment.getUser().equals(accessToken.getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }
        comment.getPost().removeCommentSize(comment.getPost().getCommentSize());
        commentRepository.delete(comment);
    }


}