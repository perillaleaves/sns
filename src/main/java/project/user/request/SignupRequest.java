package project.user.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {

    private String email;
    private String name;
    private String nickName;
    private String password;

    @Builder
    public SignupRequest(String email, String name, String nickName, String password) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
    }

}