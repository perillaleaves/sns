package project.reComment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.APIError;
import project.advice.exception.CommentNotFoundException;
import project.comment.domain.Comment;
import project.comment.repository.CommentRepository;
import project.reComment.domain.ReComment;
import project.reComment.repository.ReCommentRepository;
import project.reComment.request.ReCommentRequest;
import project.user.domain.User;

@Service
@Transactional
public class ReCommentApiService {

    private final CommentRepository commentRepository;
    private final ReCommentRepository reCommentRepository;

    public ReCommentApiService(CommentRepository commentRepository, ReCommentRepository reCommentRepository) {
        this.commentRepository = commentRepository;
        this.reCommentRepository = reCommentRepository;
    }

    public void create(Long commentId, ReCommentRequest request, User user) {
        validation(request);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        ReComment reComment = ReComment.builder()
                .content(request.getContent())
                .reCommentLikeSize(0L)
                .user(user)
                .comment(comment)
                .build();

        comment.increaseReCommentSize(comment.getReCommentSize());
        reCommentRepository.save(reComment);
    }

    private static void validation(ReCommentRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }

}