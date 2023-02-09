package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginResponse {

    private Long userId;
    private String accessToken;

    public UserLoginResponse(Long userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }
}