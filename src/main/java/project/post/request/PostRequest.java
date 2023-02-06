package project.post.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequest {

    private Long userId;

    private String content;

    @Builder
    public PostRequest(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}