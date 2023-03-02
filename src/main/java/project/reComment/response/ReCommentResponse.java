package project.reComment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReCommentResponse {

    private ReCommentListResponse reCommentListResponse;

    public ReCommentResponse(ReCommentListResponse reCommentListResponse) {
        this.reCommentListResponse = reCommentListResponse;
    }

}