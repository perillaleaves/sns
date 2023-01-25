package project.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.domain.user.UserProfileImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponse {

    private Long postId;
    private Long userId;
    private UserProfileImage profileImage;
    private String nickName;

    private List<PostImagesResponse> postImages = new ArrayList<>();

    private String content;
    private boolean isLike;
    private Long postLikeSize;
    private Long commentSize;

    private LocalDateTime updatedAt;

    public PostDetailResponse(Long postId, Long userId, UserProfileImage profileImage, String nickName, List<PostImagesResponse> postImages, String content, boolean isLike, Long postLikeSize, Long commentSize, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.profileImage = profileImage;
        this.nickName = nickName;
        this.postImages = postImages;
        this.content = content;
        this.isLike = isLike;
        this.postLikeSize = postLikeSize;
        this.commentSize = commentSize;
        this.updatedAt = updatedAt;
    }
}