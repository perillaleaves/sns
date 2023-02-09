package project.follow.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingResponse {

    private FollowingListResponse followingListResponse;

    public FollowingResponse(FollowingListResponse followingListResponse) {
        this.followingListResponse = followingListResponse;
    }
}