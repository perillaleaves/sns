package project.comment.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSliceResponse {

    private Long commentId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private String content;
    private Long reCommentSize;
    private LocalDateTime updatedAt;

    @QueryProjection
    public CommentSliceResponse(Long commentId, String userProfileImageUrl, String userName, String nickName, String content, Long reCommentSize, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.content = content;
        this.reCommentSize = reCommentSize;
        this.updatedAt = updatedAt;
    }

}