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

    @OneToOne(mappedBy = "userProfileImage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    private String userProfileImageURL;

    @Builder
    public UserProfileImage(Long id, User user, String userProfileImageURL) {
        this.id = id;
        this.user = user;
        this.userProfileImageURL = userProfileImageURL;
    }
}