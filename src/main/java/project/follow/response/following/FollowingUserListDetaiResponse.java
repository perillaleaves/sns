package project.follow.response.following;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingUserListDetaiResponse {

    private Long followId;
    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private Boolean isFollowing;

    public FollowingUserListDetaiResponse(Long followId, Long userId, String userProfileImageUrl, String userName, String nickName, Boolean isFollowing) {
        this.followId = followId;
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.isFollowing = isFollowing;
    }
}