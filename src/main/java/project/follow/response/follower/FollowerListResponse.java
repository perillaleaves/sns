package project.follow.response.follower;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.follow.response.following.UserIsFollowingResponse;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerListResponse {

    private UserIsFollowingResponse userIsFollowingResponse;
    private List<FollowerUserListDetailResponse> followerUserListResponseList;
    private Boolean hasNext;

    public FollowerListResponse(UserIsFollowingResponse userIsFollowingResponse, List<FollowerUserListDetailResponse> followerUserListResponseList, Boolean hasNext) {
        this.userIsFollowingResponse = userIsFollowingResponse;
        this.followerUserListResponseList = followerUserListResponseList;
        this.hasNext = hasNext;
    }

}