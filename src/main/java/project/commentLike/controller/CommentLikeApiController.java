package project.commentLike.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.commentLike.service.CommentLikeApiService;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.user.domain.User;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentLikeApiController {

    private final CommentLikeApiService commentLikeApiService;

    public CommentLikeApiController(CommentLikeApiService commentLikeApiService) {
        this.commentLikeApiService = commentLikeApiService;
    }

    @PostMapping("/comment/{commentId}/like")
    public Response<ValidationResponse> like(@PathVariable("commentId") Long commentId, HttpServletRequest httpServletRequest) {
        User loginUser = (User) httpServletRequest.getAttribute("user");
        commentLikeApiService.addLike(commentId, loginUser);
        return new Response<>(new ValidationResponse("Like", "좋아요"));
    }

    @DeleteMapping("/comment/{commentId}/unlike")
    public Response<ValidationResponse> unLike(@PathVariable("commentId") Long commentId, HttpServletRequest httpServletRequest) {
        User loginUser = (User) httpServletRequest.getAttribute("user");
        commentLikeApiService.removeLike(commentId, loginUser);
        return new Response<>(new ValidationResponse("UnLike", "좋아요 취소"));
    }

}