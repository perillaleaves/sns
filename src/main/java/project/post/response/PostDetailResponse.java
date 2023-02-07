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
    private String content;
    private boolean isLike;
    private Long postLikeSize;
    private Long commentSize;
    private boolean isPostEdit;
    private LocalDateTime updatedAt;

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

    public PostDetailResponse(Long postId, Long userId, String userProfileImageURL, String nickName, String content, boolean isLike, Long postLikeSize, Long commentSize, boolean isPostEdit, LocalDateTime updatedAt, String postImageUrl1, String postImageUrl2, String postImageUrl3, String postImageUrl4, String postImageUrl5, String postImageUrl6, String postImageUrl7, String postImageUrl8, String postImageUrl9, String postImageUrl10) {
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
    }
}