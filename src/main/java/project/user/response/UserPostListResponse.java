package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPostListResponse {

    private Long postId;
    private Long postImageId;
    private String postImageUrl;

    public UserPostListResponse(Long postId, Long postImageId, String postImageUrl) {
        this.postId = postId;
        this.postImageId = postImageId;
        this.postImageUrl = postImageUrl;
    }

}