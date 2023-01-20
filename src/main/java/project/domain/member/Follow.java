package project.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.CreatedAtEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends CreatedAtEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member toMember;

}
