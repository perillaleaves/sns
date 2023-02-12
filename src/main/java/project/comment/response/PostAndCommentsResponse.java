package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.user.response.UserSimpleResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAndCommentsResponse {

    private UserSimpleResponse userSimpleResponse;

    private List<CommentResponse> commentResponses = new ArrayList<>();

    public PostAndCommentsResponse(UserSimpleResponse userSimpleResponse , List<CommentResponse> commentResponses) {
        this.userSimpleResponse = userSimpleResponse;
        this.commentResponses = commentResponses;
    }
}