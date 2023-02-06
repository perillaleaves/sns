package project.user.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.user.domain.UserProfileImage;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {

    private UserProfileImage userProfileImageURL;
    private String email;
    private String name;
    private String nickName;
    private String password;

    @Builder
    public SignupRequest(UserProfileImage userProfileImageURL, String email, String name, String nickName, String password) {
        this.userProfileImageURL = userProfileImageURL;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
    }

}