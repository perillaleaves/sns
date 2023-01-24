package project.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.CreatedAtEntity;
import project.domain.user.User;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@Table(name = "likes")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
        post.addPostLikeSize(post.getLikeSize());
    }

    public static Like addLike(Post post, HttpServletRequest httpServletRequest) {
        User userId = (User) httpServletRequest.getAttribute("userId");
        return Like.builder()
                .post(post)
                .user(userId)
                .build();
    }

    public boolean isLikeOf(User user) {
        return user.hasId(user.getId());
    }

}