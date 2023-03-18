package project.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userProfileImageId")
    private Long id;

    private String userProfileImageURL;

    @OneToOne(mappedBy = "userProfileImage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @Builder
    public UserProfileImage(Long id, String userProfileImageUrl, User user) {
        this.id = id;
        this.userProfileImageURL = userProfileImageUrl;
        this.user = user;
    }

    public void updateUserProfileImageUrl(String userProfileImageURL) {
        this.userProfileImageURL = userProfileImageURL;
    }

}