package project.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.CreatedAtEntity;
import project.config.GenerateToken;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserToken extends CreatedAtEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTokenId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String accessToken;

    @Builder
    public UserToken(User user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public static UserToken create(User user, String email) {
        return UserToken.builder()
                .user(user)
                .accessToken(GenerateToken.generatedToken(user, email))
                .build();
    }


}