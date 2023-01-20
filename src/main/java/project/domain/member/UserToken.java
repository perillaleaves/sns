package project.domain.member;

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

    @Id @GeneratedValue
    @Column(name = "userTokenId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member memberId;

    private String accessToken;

    @Builder
    public UserToken(Member memberId, String accessToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
    }

    public static UserToken create(Member memberId, String email) {
        return UserToken.builder()
                .memberId(memberId)
                .accessToken(GenerateToken.generatedToken(memberId, email))
                .build();
    }


}
