package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginResponse {

    private Long userId;
    private String userProfileImageUrl;
    private String nickName;
    private String accessToken;

    public UserLoginResponse(Long userId, String userProfileImageUrl, String nickName, String accessToken) {
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.nickName = nickName;
        this.accessToken = accessToken;
    }

}