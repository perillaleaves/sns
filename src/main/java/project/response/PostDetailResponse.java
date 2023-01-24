package project.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponse {

    private Long postId;
    private Long userId;
    private String profileImage;
    private String nickName;

    private List<PostImagesResponse> postImages = new ArrayList<>();

    private String content;
    private boolean isLike;
    private Long likeSize;
    private Long commentSize;

    private LocalDateTime updatedAt;

    public PostDetailResponse(Long postId, Long userId, String profileImage, String nickName, List<PostImagesResponse> postImages, String content, boolean isLike, Long likeSize, Long commentSize, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.profileImage = profileImage;
        this.nickName = nickName;
        this.postImages = postImages;
        this.content = content;
        this.isLike = isLike;
        this.likeSize = likeSize;
        this.commentSize = commentSize;
        this.updatedAt = updatedAt;
    }
}
