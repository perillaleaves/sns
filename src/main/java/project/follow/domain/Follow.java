package project.follow.domain;

import lombok.*;
import project.common.CreatedAtEntity;
import project.user.domain.User;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User toUser;

}