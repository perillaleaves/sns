package project.comment.domain;

import lombok.*;
import project.common.BaseEntity;
import project.post.domain.Post;
import project.user.domain.User;

import javax.persistence.*;

@Builder
@AllArgsConstructor
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

    public void update(String content) {
        this.content = content;
    }

}