package project.user.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {

    private String email;
    private String userName;
    private String nickName;
    private String password;

    public SignupRequest(String email, String userName, String nickName, String password) {
        this.email = email;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
    }

}