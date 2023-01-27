package project.user.request;

import lombok.AccessLevel;
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

}