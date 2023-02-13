package project.comment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.user.response.UserSimpleResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListResponse {

    private UserSimpleResponse userSimpleResponse;

    private List<CommentListDetailResponse> commentListDetailResponse = new ArrayList<>();

    private Boolean hasNext;

    public CommentListResponse(UserSimpleResponse userSimpleResponse, List<CommentListDetailResponse> commentListDetailResponse, Boolean hasNext) {
        this.userSimpleResponse = userSimpleResponse;
        this.commentListDetailResponse = commentListDetailResponse;
        this.hasNext = hasNext;
    }
}