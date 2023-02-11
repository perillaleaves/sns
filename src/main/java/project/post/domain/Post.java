package project.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.comment.domain.Comment;
import project.common.BaseEntity;
import project.postLike.domain.PostLike;
import project.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postImageId")
    private PostImage postImage;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> PostLikes = new ArrayList<>();

    @Builder
    public Post(Long id, String content, Long commentSize, Long postLikeSize, User user, PostImage postImage, List<Comment> comments, List<PostLike> postLikes) {
        this.id = id;
        this.content = content;
        this.commentSize = commentSize;
        this.postLikeSize = postLikeSize;
        this.user = user;
        this.postImage = postImage;
        this.comments = comments;
        this.PostLikes = postLikes;
    }

    public void updatePostContent(String content) {
        this.content = content;
    }

    public void increasePostLikeSize(Long likeSize) {
        this.postLikeSize = ++likeSize;
    }

    public void decreasePostLikeSize(Long likeSize) {
        this.postLikeSize = --likeSize;
    }

    public void increaseCommentSize(Long commentSize) {
        this.commentSize = ++commentSize;
    }

    public void decreaseCommentSize(Long commentSize) {
        this.commentSize = --commentSize;
    }

}