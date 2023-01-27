package project.advice.exception;

public class AccessTokenNotFoundException extends NotFoundException {

    private static final String CODE = "AccessTokenNotFound";
    private static final String MESSAGE = "토큰을 찾을 수 없습니다.";

    public AccessTokenNotFoundException() {
        super(CODE, MESSAGE);
    }

}