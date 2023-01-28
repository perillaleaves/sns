package project.post.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.user.domain.UserProfileImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponse {

    private Long postId;
    private Long userId;
//    private UserProfileImage profileImage;
    private String nickName;

//    private List<PostImagesResponse> postImages = new ArrayList<>();

    private String content;
    private boolean isLike;
    private Long postLikeSize;
    private Long commentSize;

    private LocalDateTime updatedAt;

    public PostDetailResponse(Long postId, Long userId, String nickName, String content, boolean isLike, Long postLikeSize, Long commentSize, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.nickName = nickName;
        this.content = content;
        this.isLike = isLike;
        this.postLikeSize = postLikeSize;
        this.commentSize = commentSize;
        this.updatedAt = updatedAt;
    }
}