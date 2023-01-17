package project.domain.post;

import project.common.BaseEntity;
import project.domain.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "postId")
    private Long id;

    private String content;
    private Long commentSize;
    private Long likeSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getCommentSize() {
        return commentSize;
    }

    public Long getLikeSize() {
        return likeSize;
    }

    public Member getMember() {
        return member;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Post() {
    }
}
