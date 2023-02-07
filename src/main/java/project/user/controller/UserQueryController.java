package project.user.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.user.response.ProfileResponse;
import project.user.response.UserProfileResponse;
import project.user.service.UserQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserQueryController {

    private final UserQueryService userQueryService;

    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/user/{userId}")
    public Response<UserProfileResponse> getUserProfile(@PathVariable(name = "userId") Long userId,
                                                        @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
                                                        HttpServletRequest httpServletRequest) {
        Long myId = (Long) httpServletRequest.getAttribute("userId");
        ProfileResponse userProfile = userQueryService.findUserProfile(userId, myId, pageable);
        return new Response<>(new UserProfileResponse(userProfile));
    }
}