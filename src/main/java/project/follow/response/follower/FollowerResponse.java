package project.follow.response.follower;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.follow.response.following.FollowingListResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerResponse {

    private FollowingListResponse followingListResponse;

    public FollowerResponse(FollowingListResponse followingListResponse) {
        this.followingListResponse = followingListResponse;
    }
}