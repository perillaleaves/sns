package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListDetailResponse {

    private Long commentId;
    private String userProfileImage;
    private String userName;
    private String nickName;
    private String commentContent;
    private Long commentListSize;
    private Long reCommentSize;
    private Boolean isCommentLike;
    private Boolean isCommentOwner;
    private LocalDateTime updatedAt;

    public CommentListDetailResponse(Long commentId, String userProfileImage, String userName, String nickName, String commentContent, Long commentListSize, Long reCommentSize, Boolean isCommentLike, Boolean isCommentOwner, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.userProfileImage = userProfileImage;
        this.userName = userName;
        this.nickName = nickName;
        this.commentContent = commentContent;
        this.commentListSize = commentListSize;
        this.reCommentSize = reCommentSize;
        this.isCommentLike = isCommentLike;
        this.isCommentOwner = isCommentOwner;
        this.updatedAt = updatedAt;
    }

}