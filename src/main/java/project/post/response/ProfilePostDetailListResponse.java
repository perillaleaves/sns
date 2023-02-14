package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfilePostDetailListResponse {

    private Long postId;
    private Long postImageId;
    private String postImageUrl;

    public ProfilePostDetailListResponse(Long postId, Long postImageId, String postImageUrl) {
        this.postId = postId;
        this.postImageId = postImageId;
        this.postImageUrl = postImageUrl;
    }

}