package project.domain.member;

import lombok.Builder;
import lombok.Getter;
import project.common.BaseEntity;
import project.exception.APIError;
import project.request.SignupRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.regex.Pattern;


@Entity
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "memberId")
    private Long id;

    private String profileImage;
    private String email;
    private String name;
    private String nickName;
    private String password;
    private String content;
    private Long postSize;
    private Long followSize;
    private Long followingSize;

    protected Member() {
    }

    @Builder
    public Member(String profileImage, String email, String name, String nickName, String password) {
        this.profileImage = profileImage;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
    }

    public static Member signup(SignupRequest request) {
        validate(request);

        return Member.builder()
                .profileImage(request.getProfileImage())
                .email(request.getEmail())
                .name(request.getName())
                .nickName(request.getNickName())
                .password(request.getPassword())
                .build();
    }

    private static void validate(SignupRequest request) {
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", request.getEmail());

        if (!email_validate) {
            throw new APIError("InvalidEmail", "이메일을 양식에 맞게 입력해주세요.");
        }
        if (2 > request.getName().length() && request.getName().length() > 10) {
            throw new APIError("InvalidName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (2 > request.getNickName().length() && request.getNickName().length() > 10) {
            throw new APIError("InvalidName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (5 > request.getPassword().length() && request.getPassword().length() > 10) {
            throw new APIError("InvalidName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }

    }


}
