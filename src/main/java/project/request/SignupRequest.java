package project.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.domain.user.UserProfileImage;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {

    private UserProfileImage userProfileImageURL;
    private String email;
    private String name;
    private String nickName;
    private String password;

}