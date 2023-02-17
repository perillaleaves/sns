package project.reComment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReCommentListDetailResponse {

    private Long reCommentId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private String content;
    private Long reCommentLikeSize;
    private Boolean isReCommentLike;
    private Boolean isReCommentOwner;
    private LocalDateTime updatedAt;

    public ReCommentListDetailResponse(Long reCommentId, String userProfileImageUrl, String userName, String nickName, String content, Long reCommentLikeSize, Boolean isReCommentOwner, Boolean isReCommentLike, LocalDateTime updatedAt) {
        this.reCommentId = reCommentId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.content = content;
        this.reCommentLikeSize = reCommentLikeSize;
        this.isReCommentOwner = isReCommentOwner;
        this.isReCommentLike = isReCommentLike;
        this.updatedAt = updatedAt;
    }

}