package project.user.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileEditRequest {

    private String userName;
    private String nickName;
    private String content;

    public ProfileEditRequest(String userName, String nickName, String content) {
        this.userName = userName;
        this.nickName = nickName;
        this.content = content;
    }
}