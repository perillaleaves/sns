package project.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {

    private String profileImage;
    private String email;
    private String name;
    private String nickName;
    private String password;

}