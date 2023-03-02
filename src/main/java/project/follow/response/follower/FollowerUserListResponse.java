package project.follow.response.follower;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerUserListResponse {

    private Long followId;
    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;

    @QueryProjection
    public FollowerUserListResponse(Long followId, Long userId, String userProfileImageUrl, String userName, String nickName) {
        this.followId = followId;
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
    }

}