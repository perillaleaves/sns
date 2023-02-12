package project.advice.exception;

public class CommentLikeNotFoundException extends NotFoundException {

    private static final String CODE = "CommentLikeNotFound";
    private static final String MESSAGE = "댓글 좋아요를 찾을 수 없습니다.";

    public CommentLikeNotFoundException() {
        super(CODE, MESSAGE);
    }

}