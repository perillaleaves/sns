package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailResponse {

    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private Long postSize;
    private Long followerSize;
    private Long followingSize;
    private Boolean isProfileOwner;

    public UserDetailResponse(Long userId, String userProfileImageUrl, String userName, String nickName, Long postSize, Long followerSize, Long followingSize, Boolean isProfileOwner) {
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.postSize = postSize;
        this.followerSize = followerSize;
        this.followingSize = followingSize;
        this.isProfileOwner = isProfileOwner;
    }
}