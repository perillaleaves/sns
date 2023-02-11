package project.reCommentLike.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.reCommentLike.service.ReCommentLikeService;
import project.user.domain.User;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReCommentLikeApiController {

    private final ReCommentLikeService reCommentLikeService;

    public ReCommentLikeApiController(ReCommentLikeService reCommentLikeService) {
        this.reCommentLikeService = reCommentLikeService;
    }

    @PostMapping("/recomment/{recommentId}/like")
    public Response<ValidationResponse> like(@PathVariable("recommentId") Long reCommentId, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getAttribute("user");
        reCommentLikeService.addLike(reCommentId, user);
        return new Response<>(new ValidationResponse("Like", "좋아요"));
    }

    @DeleteMapping("/recomment/{recommentId}/unlike")
    public Response<ValidationResponse> unLike(@PathVariable("recommentId") Long reCommentId, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getAttribute("user");
        reCommentLikeService.removeLike(reCommentId, user);
        return new Response<>(new ValidationResponse("UnLike", "좋아요 취소"));
    }

}