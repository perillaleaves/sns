package project.follow.response.following;

import lombok.Getter;

@Getter
public class UserIsFollowingResponse {

    private final Long userId;
    private final String userProfileImageUrl;
    private final String userName;
    private final String nickName;
    private final Boolean isFollowing;

    public UserIsFollowingResponse(Long userId, String userProfileImageUrl, String userName, String nickName, Boolean isFollowing) {
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.isFollowing = isFollowing;
    }

}