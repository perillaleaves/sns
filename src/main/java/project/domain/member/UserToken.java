package project.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.CreatedAtEntity;
import project.config.GenerateToken;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserToken extends CreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "userTokenId")
    private Long id;
    private Long userId;
    private String accessToken;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    @Builder
    public UserToken(Long userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public static UserToken create(Long userId, String email) {
        return UserToken.builder()
                .userId(userId)
                .accessToken(GenerateToken.generatedToken(userId, email))
                .build();
    }


}
