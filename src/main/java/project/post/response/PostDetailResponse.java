package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponse {

    private Long postId;
    private Long userId;
    private String userProfileImageURL;
    private String nickName;

    private Set<PostImagesResponse> postImages = new HashSet<>();

    private String content;
    private boolean isLike;
    private Long postLikeSize;
    private Long commentSize;
    private boolean isPostEdit;

    private LocalDateTime updatedAt;

    public PostDetailResponse(Long postId, Long userId, String userProfileImageURL, String nickName, Set<PostImagesResponse> postImages, String content, boolean isLike, Long postLikeSize, Long commentSize, boolean isPostEdit, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.userProfileImageURL = userProfileImageURL;
        this.nickName = nickName;
        this.postImages = postImages;
        this.content = content;
        this.isLike = isLike;
        this.postLikeSize = postLikeSize;
        this.commentSize = commentSize;
        this.isPostEdit = isPostEdit;
        this.updatedAt = updatedAt;
    }
}