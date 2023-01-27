package project.advice.exception;

public class CommentNotFoundException extends NotFoundException {

    private static final String CODE = "CommentNotFound";
    private static final String MESSAGE = "댓글을 찾을 수 없습니다.";

    public CommentNotFoundException() {
        super(CODE, MESSAGE);
    }

}