package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsFeedResponse {

    private NewsFeedListResponse newsFeedListResponse;

    public NewsFeedResponse(NewsFeedListResponse newsFeedListResponse) {
        this.newsFeedListResponse = newsFeedListResponse;
    }
}