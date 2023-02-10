package project.follow.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.follow.response.follower.FollowerListResponse;
import project.follow.response.follower.FollowerResponse;
import project.follow.response.following.FollowingListResponse;
import project.follow.response.following.FollowingResponse;
import project.follow.service.FollowQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FollowQueryController {

    private final FollowQueryService followQueryService;

    public FollowQueryController(FollowQueryService followQueryService) {
        this.followQueryService = followQueryService;
    }

    @GetMapping("/user/{userId}/following")
    public Response<FollowingResponse> getFollowingList(@PathVariable(name = "userId") Long userId,
                                                        @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
                                                        HttpServletRequest httpServletRequest) {
        Long myId = (Long) httpServletRequest.getAttribute("userId");
        FollowingListResponse followingList = followQueryService.findFollowingList(userId, myId, pageable);
        return new Response<>(new FollowingResponse(followingList));
    }

    @GetMapping("/user/{userId}/follower")
    public Response<FollowerResponse> getFollowerList(@PathVariable(name = "userId") Long userId,
                                                      @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
                                                      HttpServletRequest httpServletRequest) {
        Long myId = (Long) httpServletRequest.getAttribute("userId");
        FollowerListResponse followerList = followQueryService.findFollowerList(userId, myId, pageable);
        return new Response<>(new FollowerResponse(followerList));
    }

}