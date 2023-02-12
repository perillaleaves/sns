package project.advice.exception;

public class ReCommentLikeNotFoundException extends NotFoundException {

    private static final String CODE = "ReCommentLikeNotFound";
    private static final String MESSAGE = "대댓글 좋아요를 찾을 수 없습니다.";

    public ReCommentLikeNotFoundException() {
        super(CODE, MESSAGE);
    }

}