package project.follow.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingUserListResponse {

    private Long followId;
    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private Boolean isFollowing;

    @QueryProjection
    public FollowingUserListResponse(Long followId, Long userId, String userProfileImageUrl, String userName, String nickName, Boolean isFollowing) {
        this.followId = followId;
        this.userId = userId;
        this.userProfileImageUrl = "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.isFollowing = isFollowing;
    }

}