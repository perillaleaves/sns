package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfilePostDetailListResponse {

    private Long postId;
    private List<PostImagesResponse> postImageUrls;

    public ProfilePostDetailListResponse(Long postId, List<PostImagesResponse> postImageUrls) {
        this.postId = postId;
        this.postImageUrls = postImageUrls;
    }

}