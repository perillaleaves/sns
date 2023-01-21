package project.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginAndTokenRequest {

    private String accessToken;
    private String email;
    private String password;

}