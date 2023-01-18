package project.request;

import lombok.Getter;

@Getter
public class SignupRequest {

    private String profileImage;
    private String email;
    private String name;
    private String nickName;
    private String password;

    public SignupRequest() {
    }

}
