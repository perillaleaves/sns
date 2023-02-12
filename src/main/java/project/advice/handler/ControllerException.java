package project.advice.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.advice.exception.*;
import project.common.response.ErrorResponse;
import project.common.response.Response;

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

    @ExceptionHandler(ReCommentNotFoundException.class)
    public Response<ErrorResponse> handleReCommentNotFoundException(ReCommentNotFoundException rce) {
        return new Response<>(new ErrorResponse(rce.getCode(), rce.getMessage()));
    }

    @ExceptionHandler(ReCommentLikeNotFoundException.class)
    public Response<ErrorResponse> handleReCommentLikeNotFoundException(ReCommentLikeNotFoundException re) {
        return new Response<>(new ErrorResponse(re.getCode(), re.getMessage()));
    }

    @ExceptionHandler(CommentLikeNotFoundException.class)
    public Response<ErrorResponse> handleCommentLikeNotFoundException(CommentLikeNotFoundException ce) {
        return new Response<>(new ErrorResponse(ce.getCode(), ce.getMessage()));
    }

}