package project.advice.exception;

public class FollowNotFoundException extends NotFoundException {

    private static final String CODE = "FollowNotFound";
    private static final String MESSAGE = "팔로우를 찾을 수 없습니다.";

    public FollowNotFoundException() {
        super(CODE, MESSAGE);
    }

}