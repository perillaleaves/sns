package project.post.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponse {

    private Long postId;
    private Long userId;
    private String userProfileImageUrl;
    private String username;
    private String nickName;
    private String content;
    private Long postLikeSize;
    private Long commentSize;
    private LocalDateTime updatedAt;

    @QueryProjection
    public PostListResponse(Long postId, Long userId, String userProfileImageUrl, String username, String nickName, String content, Long postLikeSize, Long commentSize, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.username = username;
        this.nickName = nickName;
        this.content = content;
        this.postLikeSize = postLikeSize;
        this.commentSize = commentSize;
        this.updatedAt = updatedAt;
    }

}