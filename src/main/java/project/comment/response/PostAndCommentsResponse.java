package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.post.response.PostSummaryResponse;
import project.user.response.UserSimpleResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAndCommentsResponse {

    private UserSimpleResponse userSimpleResponse;

    private PostSummaryResponse postSummaryResponse;

    private List<CommentResponse> commentResponses = new ArrayList<>();

    public PostAndCommentsResponse(UserSimpleResponse userSimpleResponse, PostSummaryResponse postSummaryResponse, List<CommentResponse> commentResponses) {
        this.userSimpleResponse = userSimpleResponse;
        this.postSummaryResponse = postSummaryResponse;
        this.commentResponses = commentResponses;
    }
}