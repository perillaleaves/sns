package project.post.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfilePostListResponse {

    private Long postId;
    private Long postImageId;
    private String postImageUrl;

    @QueryProjection
    public ProfilePostListResponse(Long postId, Long postImageId, String postImageUrl) {
        this.postId = postId;
        this.postImageId = postImageId;
        this.postImageUrl = postImageUrl;
    }

}