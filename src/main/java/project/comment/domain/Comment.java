package project.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.BaseEntity;
import project.post.domain.Post;
import project.reComment.domain.ReComment;
import project.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long id;

    private String content;

    private Long commentLikeSize;
    private Long reCommentSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReComment> reComments = new ArrayList<>();

    @Builder
    public Comment(Long id, String content, Long commentLikeSize, Long reCommentSize, User user, Post post, List<ReComment> reComments) {
        this.id = id;
        this.content = content;
        this.commentLikeSize = commentLikeSize;
        this.reCommentSize = reCommentSize;
        this.user = user;
        this.post = post;
        this.reComments = reComments;
    }

    public void update(String content) {
        this.content = content;
    }

    public void increaseReCommentSize(Long reCommentSize) {
        this.reCommentSize = ++reCommentSize;
    }

    public void decreaseReCommentSize(Long reCommentSize) {
        this.reCommentSize = --reCommentSize;
    }
}