package project.follow.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingListResponse {

    private List<FollowingUserListResponse> followingUserListResponseList;

    public FollowingListResponse(List<FollowingUserListResponse> followingUserListResponseList) {
        this.followingUserListResponseList = followingUserListResponseList;
    }
}