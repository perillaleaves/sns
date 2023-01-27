package project.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.exception.*;
import project.response.ErrorResponse;
import project.response.Response;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(APIError.class)
    public Response<ErrorResponse> handleAPIErrorException(APIError e) {
        return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Response<ErrorResponse> handleUserNotFoundException(UserNotFoundException ue) {
        return new Response<>(new ErrorResponse(ue.getCode(), ue.getMessage()));
    }

    @ExceptionHandler(AccessTokenNotFoundException.class)
    public Response<ErrorResponse> handleAccessTokenNotFoundException(AccessTokenNotFoundException ae) {
        return new Response<>(new ErrorResponse(ae.getCode(), ae.getMessage()));
    }

    @ExceptionHandler(PostNotFoundException.class)
    public Response<ErrorResponse> handlePostNotFoundException(PostNotFoundException pe) {
        return new Response<>(new ErrorResponse(pe.getCode(), pe.getMessage()));
    }

    @ExceptionHandler(FollowNotFoundException.class)
    public Response<ErrorResponse> handleFollowNotFoundException(FollowNotFoundException fe) {
        return new Response<>(new ErrorResponse(fe.getCode(), fe.getMessage()));
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public Response<ErrorResponse> handleCommentNotFoundException(CommentNotFoundException ce) {
        return new Response<>(new ErrorResponse(ce.getCode(), ce.getMessage()));
    }

    @ExceptionHandler(PostLikeNotFoundException.class)
    public Response<ErrorResponse> handlePostLikeNotFoundException(PostLikeNotFoundException ple) {
        return new Response<>(new ErrorResponse(ple.getCode(), ple.getMessage()));
    }

}