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

    @OneToOne(mappedBy = "userProfileImage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @Builder
    public UserProfileImage(Long id, String userProfileImageURL, User user) {
        this.id = id;
        this.userProfileImageURL = userProfileImageURL;
        this.user = user;
    }
}