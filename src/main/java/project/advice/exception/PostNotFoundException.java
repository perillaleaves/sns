package project.advice.exception;

public class PostNotFoundException extends NotFoundException {

    private static final String CODE = "PostNotFound";
    private static final String MESSAGE = "게시글을 찾을 수 없습니다.";

    public PostNotFoundException() {
        super(CODE, MESSAGE);
    }
}