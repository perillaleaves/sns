package project.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponse {

    private PostDetailResponse postDetailResponse;

    public PostResponse(PostDetailResponse postDetailResponse) {
        this.postDetailResponse = postDetailResponse;
    }

}