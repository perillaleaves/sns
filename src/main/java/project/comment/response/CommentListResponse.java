package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.comment.response.PostCommentResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListResponse {

    private PostCommentResponse postCommentResponse;

    public CommentListResponse(PostCommentResponse postCommentResponse) {
        this.postCommentResponse = postCommentResponse;
    }
}