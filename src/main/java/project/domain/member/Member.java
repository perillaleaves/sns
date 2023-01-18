package project.domain.member;

import lombok.Builder;
import lombok.Getter;
import project.common.BaseEntity;
import project.common.EncryptUtils;
import project.request.SignupRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.NoSuchAlgorithmException;


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

    public static Member create(SignupRequest request) {
        try {
            return Member.builder()
                    .profileImage(request.getProfileImage())
                    .email(request.getEmail())
                    .name(request.getName())
                    .nickName(request.getNickName())
                    .password(EncryptUtils.encrypt(request.getPassword()))
                    .build();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}