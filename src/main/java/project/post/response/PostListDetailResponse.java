package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListDetailResponse {

    private Long postId;
    private Long userId;
    private String userProfileImageUrl;
    private String username;
    private String nickName;
    private List<PostImagesResponse> postImageUrls;
    private Boolean isPostLike;
    private String content;
    private Long postListSize;
    private Long commentSize;
    private Boolean isPostOwner;
    private LocalDateTime updatedAt;

    public PostListDetailResponse(Long postId, Long userId, String userProfileImageUrl, String username, String nickName, List<PostImagesResponse> postImageUrls, Boolean isPostLike, String content, Long postListSize, Long commentSize, Boolean isPostOwner, LocalDateTime updatedAt) {
        this.postId = postId;
        this.userId = userId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.username = username;
        this.nickName = nickName;
        this.postImageUrls = postImageUrls;
        this.isPostLike = isPostLike;
        this.content = content;
        this.postListSize = postListSize;
        this.commentSize = commentSize;
        this.isPostOwner = isPostOwner;
        this.updatedAt = updatedAt;
    }

}