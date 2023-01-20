package project.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.response.ErrorResponse;
import project.response.Response;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(APIError.class)
    public Response<ErrorResponse> handleAPIErrorException(APIError e) {
        return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
    }

}