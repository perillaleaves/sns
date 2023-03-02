package project.follow.response.follower;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerResponse {

    private FollowerListResponse followerListResponse;

    public FollowerResponse(FollowerListResponse followerListResponse) {
        this.followerListResponse = followerListResponse;
    }

}