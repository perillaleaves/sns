package project.follow.response.follower;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerUserListDetailResponse {

    private Long followId;
    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private Boolean isFollower;

    public FollowerUserListDetailResponse(Long followId, Long userId, String userProfileImageUrl, String userName, String nickName, Boolean isFollower) {
        this.followId = followId;
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.isFollower = isFollower;
    }
}