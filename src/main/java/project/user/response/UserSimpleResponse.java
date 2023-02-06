package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSimpleResponse {

    private Long userId;
    private String userProfileImageUrl;
    private String nickName;

    public UserSimpleResponse(Long userId, String userProfileImageUrl, String nickName) {
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.nickName = nickName;
    }
}