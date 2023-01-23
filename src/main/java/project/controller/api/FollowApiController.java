package project.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.api.FollowApiService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class FollowApiController {

    private final FollowApiService followApiService;

    @PostMapping("/{userId}/follow")
    public Response<ValidationResponse> follow(@PathVariable("userId") Long userId, HttpServletRequest httpServletRequest) {
        followApiService.follow(userId, httpServletRequest);
        return new Response<>(new ValidationResponse("Follow", "팔로우 요청"));
    }

}
