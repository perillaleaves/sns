package project.user.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    private String accessToken;
    private String email;
    private String password;

    public LoginRequest(String accessToken, String email, String password) {
        this.accessToken = accessToken;
        this.email = email;
        this.password = password;
    }

}