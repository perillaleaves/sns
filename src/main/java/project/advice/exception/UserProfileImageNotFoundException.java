package project.advice.exception;

public class UserProfileImageNotFoundException extends NotFoundException {

    private static final String CODE = "UserProfileImageNotFound";
    private static final String MESSAGE = "유저 이미지를 찾을 수 없습니다.";

    public UserProfileImageNotFoundException() {
        super(CODE, MESSAGE);
    }

}