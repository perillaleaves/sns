package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsFeedListResponse {

    private List<PostListDetailResponse> postListDetailResponses;
    private Boolean hasNext;

    public NewsFeedListResponse(List<PostListDetailResponse> postListDetailResponses, Boolean hasNext) {
        this.postListDetailResponses = postListDetailResponses;
        this.hasNext = hasNext;
    }

}