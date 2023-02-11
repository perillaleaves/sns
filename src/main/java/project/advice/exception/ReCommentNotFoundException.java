package project.advice.exception;

public class ReCommentNotFoundException extends NotFoundException{

    private static final String CODE = "ReCommentNotFound";
    private static final String MESSAGE = "대댓글을 찾을 수 없습니다.";

    public ReCommentNotFoundException() {
        super(CODE, MESSAGE);
    }

}