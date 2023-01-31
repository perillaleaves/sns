package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCommentResponse {

    private Long postId;
    private String userProfileImage;
    private String nickName;
    private String postContent;
    private LocalDateTime updatedAt;

    private Set<CommentResponse> commentResponses = new HashSet<>();

    public PostCommentResponse(Long postId, String userProfileImage, String nickName, String postContent, LocalDateTime updatedAt, Set<CommentResponse> commentResponses) {
        this.postId = postId;
        this.userProfileImage = userProfileImage;
        this.nickName = nickName;
        this.postContent = postContent;
        this.updatedAt = updatedAt;
        this.commentResponses = commentResponses;
    }
}