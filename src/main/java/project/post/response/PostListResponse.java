package project.post.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponse {

    private Long postId;
    private Long userId;
    private String userProfileImageUrl;
    private String username;
    private String nickName;
    private String postImageUrl1;
    private String postImageUrl2;
    private String postImageUrl3;
    private String postImageUrl4;
    private String postImageUrl5;
    private String postImageUrl6;
    private String postImageUrl7;
    private String postImageUrl8;
    private String postImageUrl9;
    private String postImageUrl10;
    private String content;
    private Long postLikeSize;
    private Long commentSize;
    private LocalDateTime updatedAt;

    @QueryProjection
    public PostListResponse(Long postId, Long userId, String userProfileImageUrl, String username, String nickName, String postImageUrl1, String postImageUrl2, String postImageUrl3, String postImageUrl4, String postImageUrl5, String postImageUrl6, String postImageUrl7, String postImageUrl8, String postImageUrl9, String postImageUrl10, String content, Long postLikeSize, Long commentSize, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.username = username;
        this.nickName = nickName;
        this.postImageUrl1 = postImageUrl1;
        this.postImageUrl2 = postImageUrl2;
        this.postImageUrl3 = postImageUrl3;
        this.postImageUrl4 = postImageUrl4;
        this.postImageUrl5 = postImageUrl5;
        this.postImageUrl6 = postImageUrl6;
        this.postImageUrl7 = postImageUrl7;
        this.postImageUrl8 = postImageUrl8;
        this.postImageUrl9 = postImageUrl9;
        this.postImageUrl10 = postImageUrl10;
        this.content = content;
        this.postLikeSize = postLikeSize;
        this.commentSize = commentSize;
        this.updatedAt = updatedAt;
    }
}