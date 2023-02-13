package project.reComment.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReCommentListResponse {

    private List<ReCommentListDetailResponse> reCommentListDetailResponseList;

    private Boolean hasNext;

    public ReCommentListResponse(List<ReCommentListDetailResponse> reCommentListDetailResponseList, Boolean hasNext) {
        this.reCommentListDetailResponseList = reCommentListDetailResponseList;
        this.hasNext = hasNext;
    }

}