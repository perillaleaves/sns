package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponse {

    private ProfileResponse profileResponse;

    public UserProfileResponse(ProfileResponse profileResponse) {
        this.profileResponse = profileResponse;
    }

}