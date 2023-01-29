package project.postImages.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.post.domain.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImageRequest {

    private String postImageUrl;
    private Post post;

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}
