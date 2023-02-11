package project.reCommentLike.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.APIError;
import project.advice.exception.ReCommentNotFoundException;
import project.reComment.domain.ReComment;
import project.reComment.repository.ReCommentRepository;
import project.reCommentLike.domain.ReCommentLike;
import project.reCommentLike.repository.ReCommentLikeRepository;
import project.user.domain.User;

@Service
@Transactional
public class ReCommentLikeService {

    private final ReCommentRepository reCommentRepository;
    private final ReCommentLikeRepository reCommentLikeRepository;

    public ReCommentLikeService(ReCommentRepository reCommentRepository, ReCommentLikeRepository reCommentLikeRepository) {
        this.reCommentRepository = reCommentRepository;
        this.reCommentLikeRepository = reCommentLikeRepository;
    }

    public void addLike(Long reCommentId, User user) {
        existsValidate(reCommentId, user);
        ReComment reComment = reCommentRepository.findById(reCommentId)
                .orElseThrow(ReCommentNotFoundException::new);

        ReCommentLike reCommentLike = ReCommentLike.builder()
                .user(user)
                .reComment(reComment)
                .build();

        reComment.increaseReCommentLikeSize(reComment.getReCommentLikeSize());
        reCommentLikeRepository.save(reCommentLike);
    }

    public void removeLike(Long reCommentId, User user) {
        ReCommentLike reCommentLike = reCommentLikeRepository.findByReCommentIdAndUserId(reCommentId, user.getId())
                .orElseThrow(ReCommentNotFoundException::new);

        reCommentLike.getReComment().decreaseReCommentLikeSize(reCommentLike.getReComment().getReCommentLikeSize());
        reCommentLikeRepository.delete(reCommentLike);
    }

    private void existsValidate(Long reCommentId, User user) {
        if (reCommentLikeRepository.existsReCommentLikeByReCommentIdAndUserId(reCommentId, user.getId())) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }
    }

}