package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.post.response.PostDetailResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponse {

    private PostDetailResponse postDetailResponse;

    public PostResponse(PostDetailResponse postDetailResponse) {
        this.postDetailResponse = postDetailResponse;
    }

}