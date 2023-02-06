package project.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.BaseEntity;
import project.post.domain.Post;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Comment parent;

//    @Builder.Default
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<Comment> child = new ArrayList<>();

    @Builder
    public Comment(String content, Long commentLikeSize, User user, Post post, Comment parent) {
        this.content = content;
        this.commentLikeSize = commentLikeSize;
        this.user = user;
        this.post = post;
        this.parent = parent;
    }

    public void update(String content) {
        this.content = content;
    }

}