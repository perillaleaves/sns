package project.domain.member;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Follow {

    @Id @GeneratedValue
    @Column(name = "followId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member toMember;

    private LocalDateTime createdAt;

    protected Follow() {
    }
}
