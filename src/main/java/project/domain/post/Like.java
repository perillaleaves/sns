package project.domain.post;

import project.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Like {

    @Id @GeneratedValue
    @Column(name = "likeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Post getPost() {
        return post;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Like() {
    }
}
