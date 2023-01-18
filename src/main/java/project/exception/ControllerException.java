package project.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.response.ErrorResponse;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(APIError.class)
    public ErrorResponse handleAPIErrorException(APIError e) {
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

}