package project.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class userToken {

    @Id @GeneratedValue
    @Column(name = "userTokenId")
    private Long id;
    private Long userId;
    private String accessToken;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public userToken() {
    }
}
