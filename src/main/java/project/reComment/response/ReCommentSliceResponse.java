package project.reComment.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReCommentSliceResponse {

    private Long reCommentId;
    private String userProfileImageUrl;
    private String userName;
    private String nickName;
    private String content;
    private Long reCommentLikeSize;
    private LocalDateTime updatedAt;

    @QueryProjection
    public ReCommentSliceResponse(Long reCommentId, String userProfileImageUrl, String userName, String nickName, String content, Long reCommentLikeSize, LocalDateTime updatedAt) {
        this.reCommentId = reCommentId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
        this.nickName = nickName;
        this.content = content;
        this.reCommentLikeSize = reCommentLikeSize;
        this.updatedAt = updatedAt;
    }

}