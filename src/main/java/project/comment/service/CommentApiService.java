package project.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.APIError;
import project.advice.exception.CommentNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.comment.domain.Comment;
import project.comment.repository.CommentRepository;
import project.comment.request.CommentRequest;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.user.domain.User;

@Service
@Transactional
public class CommentApiService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentApiService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void create(Long postId, CommentRequest request, User user) {
        validation(request);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.builder()
                .content(request.getContent())
                .commentLikeSize(0L)
                .reCommentSize(0L)
                .user(user)
                .post(post)
                .build();

        post.addCommentSize(post.getCommentSize());
        commentRepository.save(comment);
    }

    public void update(Long commentId, CommentRequest request, Long userId) {
        validation(request);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        loginValidate(userId, comment);

        comment.update(request.getContent());
    }

    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        loginValidate(userId, comment);

        comment.getPost().removeCommentSize(comment.getPost().getCommentSize());
        commentRepository.delete(comment);
    }

    private static void validation(CommentRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }

    private static void loginValidate(Long userId, Comment comment) {
        if (!comment.getUser().getId().equals(userId)) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }
    }

}