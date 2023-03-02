package project.search.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchUserListDetailResponse {

    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private Long followerSize;
    private Boolean isFollowing;

    public SearchUserListDetailResponse(Long userId, String userProfileImageUrl, String userName, String nickName, Long followerSize, Boolean isFollowing) {
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.followerSize = followerSize;
        this.isFollowing = isFollowing;
    }

}