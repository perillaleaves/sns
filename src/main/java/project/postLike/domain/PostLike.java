package project.postLike.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.common.domain.CreatedAtEntity;
import project.post.domain.Post;
import project.user.domain.User;

import javax.persistence.*;

@Table(name = "postLikes")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postLikeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    public boolean isLikeOf(User user) {
        return user.hasId(user.getId());
    }

}