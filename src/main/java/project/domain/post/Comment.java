package project.domain.post;

import project.common.BaseEntity;
import project.domain.member.Member;

import javax.persistence.*;

@Entity
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "commentId")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Member getMember() {
        return member;
    }

    public Post getPost() {
        return post;
    }

    public Comment() {
    }
}
