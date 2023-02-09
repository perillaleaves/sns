package project.follow.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingUserListResponse {

    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private boolean isFollower;
    private boolean isFollowing;

    @QueryProjection
    public FollowingUserListResponse(Long userId, String userProfileImageUrl, String userName, String nickName, boolean isFollower, boolean isFollowing) {
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.isFollower = isFollower;
        this.isFollowing = isFollowing;
    }
}