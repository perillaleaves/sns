package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImagesResponse {

    private Long postImageId;
    private String imageUrl;

    public PostImagesResponse(Long postImageId, String imageUrl) {
        this.postImageId = postImageId;
        this.imageUrl = imageUrl;
    }
}