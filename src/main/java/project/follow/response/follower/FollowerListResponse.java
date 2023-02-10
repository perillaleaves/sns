package project.follow.response.follower;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerListResponse {

    private List<FollowerUserListDetailResponse> followerUserListResponseList;
    private Boolean hasNext;

    public FollowerListResponse(List<FollowerUserListDetailResponse> followerUserListResponseList, Boolean hasNext) {
        this.followerUserListResponseList = followerUserListResponseList;
        this.hasNext = hasNext;
    }
}