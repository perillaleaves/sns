package project.post.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfilePostListResponse {

    private Long postId;

    @QueryProjection
    public ProfilePostListResponse(Long postId) {
        this.postId = postId;
    }

}