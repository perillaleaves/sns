package project.exception;

public class UserNotFoundException extends NotFoundException{

    private static final String CODE = "UserNotFound";
    private static final String MESSAGE = "유저를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(CODE, MESSAGE);
    }

}