package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponse {

    private Long postId;
    private Long userId;
    private String userProfileImageURL;
    private String nickName;
    private String content;
    private Boolean isLike;
    private Long postLikeSize;
    private Long commentSize;
    private Boolean isPostEdit;
    private LocalDateTime updatedAt;

    private List<PostImagesResponse> postImageUrls;

    public PostDetailResponse(Long postId, Long userId, String userProfileImageURL, String nickName, String content, Boolean isLike, Long postLikeSize, Long commentSize, Boolean isPostEdit, LocalDateTime updatedAt, List<PostImagesResponse> postImageUrls) {
        this.postId = postId;
        this.userId = userId;
        this.userProfileImageURL = userProfileImageURL;
        this.nickName = nickName;
        this.content = content;
        this.isLike = isLike;
        this.postLikeSize = postLikeSize;
        this.commentSize = commentSize;
        this.isPostEdit = isPostEdit;
        this.updatedAt = updatedAt;
        this.postImageUrls = postImageUrls;
    }
}