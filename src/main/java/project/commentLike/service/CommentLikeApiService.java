package project.commentLike.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.error.APIError;
import project.advice.exception.CommentLikeNotFoundException;
import project.advice.exception.CommentNotFoundException;
import project.comment.domain.Comment;
import project.comment.repository.CommentRepository;
import project.commentLike.domain.CommentLike;
import project.commentLike.repository.CommentLikeRepository;
import project.user.domain.User;

@Service
@Transactional
public class CommentLikeApiService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeApiService(CommentRepository commentRepository, CommentLikeRepository commentLikeRepository) {
        this.commentRepository = commentRepository;
        this.commentLikeRepository = commentLikeRepository;
    }

    public void addLike(Long commentId, User loginUser) {
        existsValidate(commentId, loginUser);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        CommentLike commentLike = CommentLike.builder()
                .user(loginUser)
                .comment(comment)
                .build();

        comment.increaseCommentLikeSize(comment.getCommentLikeSize());
        commentLikeRepository.save(commentLike);
    }

    public void removeLike(Long commentId, User loginUser) {
        CommentLike commentLike = commentLikeRepository.findByCommentIdAndUserId(commentId, loginUser.getId())
                .orElseThrow(CommentLikeNotFoundException::new);

        commentLike.getComment().decreaseCommentLikeSize(commentLike.getComment().getCommentLikeSize());
        commentLikeRepository.delete(commentLike);
    }

    private void existsValidate(Long commentId, User user) {
        if (commentLikeRepository.existsCommentLikeByCommentIdAndUserId(commentId, user.getId())) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }
    }

}