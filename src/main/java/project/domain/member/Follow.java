package project.domain.member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Follow {

    @Id @GeneratedValue
    @Column(name = "followId")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member toMember;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public Member getFromMember() {
        return fromMember;
    }

    public Member getToMember() {
        return toMember;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Follow() {
    }
}
