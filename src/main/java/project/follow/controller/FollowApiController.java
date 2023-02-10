package project.follow.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.follow.service.FollowApiService;
import project.token.repository.TokenRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FollowApiController {

    private final FollowApiService followApiService;

    public FollowApiController(FollowApiService followApiService) {
        this.followApiService = followApiService;
    }

    @PostMapping("/{userId}/follow")
    public Response<ValidationResponse> follow(@PathVariable("userId") Long toUserId, HttpServletRequest httpServletRequest) {
        Long fromUserId = (Long) httpServletRequest.getAttribute("userId");
        followApiService.follow(fromUserId, toUserId);
        return new Response<>(new ValidationResponse("Follow", "팔로우 요청"));
    }

    @DeleteMapping("{userId}/unfollow")
    public Response<ValidationResponse> unfollow(@PathVariable("userId") Long toUserId, HttpServletRequest httpServletRequest) {
        Long fromUserId = (Long) httpServletRequest.getAttribute("userId");
        followApiService.unfollow(fromUserId, toUserId);
        return new Response<>(new ValidationResponse("UnFollow", "언팔로우"));
    }

}