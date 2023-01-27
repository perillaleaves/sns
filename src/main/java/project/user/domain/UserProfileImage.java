package project.user.domain;

import lombok.AccessLevel;
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

    @OneToOne(mappedBy = "userProfileImage", cascade = CascadeType.ALL)
    private User user;

    private String userProfileImageURL;


}