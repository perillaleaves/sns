package project.postLike.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.postLike.service.PostLikeApiService;
import project.user.domain.User;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PostLikeApiController {

    private final PostLikeApiService postLikeApiService;

    public PostLikeApiController(PostLikeApiService postLikeApiService) {
        this.postLikeApiService = postLikeApiService;
    }

    @PostMapping("/post/{postId}/like")
    public Response<ValidationResponse> like(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getAttribute("user");
        postLikeApiService.addLike(postId, user);
        return new Response<>(new ValidationResponse("Like", "좋아요"));
    }

    @DeleteMapping("/post/{postId}/unlike")
    public Response<ValidationResponse> unLike(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getAttribute("user");
        postLikeApiService.removeLike(postId, user);
        return new Response<>(new ValidationResponse("UnLike", "좋아요 취소"));
    }

}