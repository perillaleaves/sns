package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSummaryResponse {

    private Long postId;
    private String userProfileImage;
    private String nickName;
    private String postContent;
    private LocalDateTime updatedAt;

    public PostSummaryResponse(Long postId, String userProfileImage, String nickName, String postContent, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userProfileImage = userProfileImage;
        this.nickName = nickName;
        this.postContent = postContent;
        this.updatedAt = updatedAt;
    }
}