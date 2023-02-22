package project.follow.response.following;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingListResponse {

    private UserIsFollowingResponse userIsFollowingResponse;
    private List<FollowingUserListDetaiResponse> followingUserListResponseList;
    private Boolean hasNext;

    public FollowingListResponse(UserIsFollowingResponse userIsFollowingResponse, List<FollowingUserListDetaiResponse> followingUserListResponseList, Boolean hasNext) {
        this.userIsFollowingResponse = userIsFollowingResponse;
        this.followingUserListResponseList = followingUserListResponseList;
        this.hasNext = hasNext;
    }

}