package project.post.domain;

import lombok.*;
import project.comment.domain.Comment;
import project.common.BaseEntity;
import project.postLike.domain.PostLike;
import project.user.domain.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;

    private String content;

    private Long commentSize;
    private Long postLikeSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostImage> postImages = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PostLike> PostLikes = new HashSet<>();

    public void updatePostContent(String content) {
        this.content = content;
    }

    public void addPostLikeSize(Long likeSize) {
        this.postLikeSize = ++likeSize;
    }

    public void removePostLikeSize(Long likeSize) {
        this.postLikeSize = --likeSize;
    }

    public void addCommentSize(Long commentSize) {
        this.commentSize = ++commentSize;
    }

    public void removeCommentSize(Long commentSize) {
        this.commentSize = --commentSize;
    }

}