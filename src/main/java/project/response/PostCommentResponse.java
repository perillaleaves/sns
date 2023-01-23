package project.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCommentResponse {

    private Long postId;
    private String userProfileImage;
    private String userName;
    private String postContent;

    private List<CommentResponse> commentResponses = new ArrayList<>();

    public PostCommentResponse(Long postId, String userProfileImage, String userName, String postContent, List<CommentResponse> commentResponses) {
        this.postId = postId;
        this.userProfileImage = userProfileImage;
        this.userName = userName;
        this.postContent = postContent;
        this.commentResponses = commentResponses;
    }
}
