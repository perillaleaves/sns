package project.follow.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FollowApiController {

    private final FollowApiService followApiService;
    private final TokenRepository tokenRepository;

    @PostMapping("/{userId}/follow")
    public Response<ValidationResponse> follow(@PathVariable("userId") Long userId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        followApiService.follow(userId, token);
        return new Response<>(new ValidationResponse("Follow", "팔로우 요청"));
    }

    @DeleteMapping("{userId}/unfollow")
    public Response<ValidationResponse> unfollow(@PathVariable("userId") Long userId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        followApiService.unfollow(token, userId);
        return new Response<>(new ValidationResponse("UnFollow", "언팔로우"));
    }

}