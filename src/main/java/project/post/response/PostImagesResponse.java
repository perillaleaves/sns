package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImagesResponse {

    private Long postImageId;
    private String postImageUrl;

    public PostImagesResponse(Long postImageId, String postImageUrl) {
        this.postImageId = postImageId;
        this.postImageUrl = postImageUrl;
    }
}