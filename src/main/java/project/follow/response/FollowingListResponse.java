package project.follow.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingListResponse {

    private Slice<FollowingUserListResponse> followingUserListResponseList;

    public FollowingListResponse(Slice<FollowingUserListResponse> followingUserListResponseList) {
        this.followingUserListResponseList = followingUserListResponseList;
    }
}