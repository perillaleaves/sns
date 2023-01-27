package project.advice.exception;

public class PostLikeNotFoundException extends NotFoundException {

    private static final String CODE = "PostLikeNotFound";
    private static final String MESSAGE = "좋아요를 찾을 수 없습니다.";

    public PostLikeNotFoundException() {
        super(CODE, MESSAGE);
    }

}
