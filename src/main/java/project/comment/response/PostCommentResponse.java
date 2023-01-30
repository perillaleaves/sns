package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCommentResponse {

    private Long postId;
    private String userProfileImage;
    private String nickName;
    private String postContent;

    private Set<CommentResponse> commentResponses = new HashSet<>();

    public PostCommentResponse(Long postId, String userProfileImage, String nickName, String postContent, Set<CommentResponse> commentResponses) {
        this.postId = postId;
        this.userProfileImage = userProfileImage;
        this.nickName = nickName;
        this.postContent = postContent;
        this.commentResponses = commentResponses;
    }
}