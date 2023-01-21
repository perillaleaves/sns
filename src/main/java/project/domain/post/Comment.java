package project.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.BaseEntity;
import project.domain.user.User;
import project.request.CommentRequest;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @Builder
    public Comment(String content, User user, Post post) {
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public static Comment create(Post post, CommentRequest request, HttpServletRequest httpServletRequest) {
        User userId = (User) httpServletRequest.getAttribute("userId");
        post.addCommentSize(post.getCommentSize());
        return Comment.builder()
                .content(request.getContent())
                .user(userId)
                .post(post)
                .build();
    }

    public void update(String content) {
        this.content = content;
    }


}
