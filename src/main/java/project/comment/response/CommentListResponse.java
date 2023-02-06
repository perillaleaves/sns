package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListResponse {

    private PostAndCommentsResponse postAndCommentsResponse;

    public CommentListResponse(PostAndCommentsResponse postAndCommentsResponse) {
        this.postAndCommentsResponse = postAndCommentsResponse;
    }
}