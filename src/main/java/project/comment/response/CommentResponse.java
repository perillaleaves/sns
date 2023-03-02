package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponse {

    private CommentListResponse commentListResponse;

    public CommentResponse(CommentListResponse commentListResponse) {
        this.commentListResponse = commentListResponse;
    }
}