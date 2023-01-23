package project.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.BaseEntity;
import project.domain.user.User;
import project.request.PostRequest;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Long likeSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @Builder
    public Post(String content, Long commentSize, Long likeSize, User user, List<PostImage> postImages, Set<Comment> comments, List<Like> likes) {
        this.content = content;
        this.commentSize = commentSize;
        this.likeSize = likeSize;
        this.user = user;
        this.postImages = postImages;
        this.comments = comments;
        this.likes = likes;
    }

    public static Post create(HttpServletRequest httpServletRequest, PostRequest request) {
        User userId = (User) httpServletRequest.getAttribute("userId");
        return Post.builder()
                .content(request.getContent())
                .commentSize(0L)
                .likeSize(0L)
                .user(userId)
                .build();
    }

    public void updatePostContent(String content) {
        this.content = content;
    }

    public void addPostLikeSize(Long likeSize) {
        this.likeSize = ++likeSize;
    }

    public void removePostLikeSize(Long likeSize) { this.likeSize = --likeSize; }

    public void addCommentSize(Long commentSize) {
        this.commentSize = ++commentSize;
    }

    public void removeCommentSize(Long commentSize) {
        this.commentSize = --commentSize;
    }

}