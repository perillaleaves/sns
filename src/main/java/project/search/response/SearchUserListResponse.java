package project.search.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchUserListResponse {

    private Long userId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private Long followerSize;

    @QueryProjection
    public SearchUserListResponse(Long userId, String userProfileImageUrl, String userName, String nickName, Long followerSize) {
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.followerSize = followerSize;
    }

}