package project.user.domain;

import lombok.*;
import project.common.BaseEntity;
import project.token.domain.UserToken;
import project.user.request.ProfileEditRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userProfileImageId")
    private UserProfileImage userProfileImage;

    private String email;
    private String name;
    private String nickName;
    private String password;

    private String content;

    private Long postSize;
    private Long followingSize;
    private Long followerSize;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserToken> userTokens = new ArrayList<>();

    public void addPostSize(Long postSize) {
        this.postSize = ++postSize;
    }

    public void removePostSize(Long postSize) {
        this.postSize = --postSize;
    }

    public void addFollowingSize(Long followingSize) {
        this.followingSize = ++followingSize;
    }

    public void addFollowerSize(Long followerSize) {
        this.followerSize = ++followerSize;
    }

    public void removeFollowingSize(Long followingSize) {
        this.followingSize = --followingSize;
    }

    public void removeFollowerSize(Long followerSize) {
        this.followerSize = --followerSize;
    }

    public void editProfile(ProfileEditRequest request) {
        this.name = request.getUserName();
        this.nickName = request.getNickName();
        this.content = request.getContent();
    }

}