package project.reComment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.comment.domain.Comment;
import project.common.BaseEntity;
import project.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " reCommentId")
    private Long id;

    private String content;

    private Long reCommentLikeSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentId")
    private Comment comment;

    @Builder
    public ReComment(String content, Long reCommentLikeSize, User user, Comment comment) {
        this.content = content;
        this.reCommentLikeSize = reCommentLikeSize;
        this.user = user;
        this.comment = comment;
    }
}