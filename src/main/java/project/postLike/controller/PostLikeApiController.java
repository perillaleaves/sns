package project.postLike.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.postLike.service.PostLikeApiService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PostLikeApiController {

    private final PostLikeApiService postLikeApiService;

    public PostLikeApiController(PostLikeApiService postLikeApiService) {
        this.postLikeApiService = postLikeApiService;
    }

    @PostMapping("/{postId}/like")
    public Response<ValidationResponse> like(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        postLikeApiService.addLike(postId, token);
        return new Response<>(new ValidationResponse("Like", "좋아요"));
    }

    @DeleteMapping("/{postId}/unlike")
    public Response<ValidationResponse> unLike(@PathVariable("postId") Long postId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        postLikeApiService.removeLike(postId, token);
        return new Response<>(new ValidationResponse("UnLike", "좋아요 취소"));
    }

}