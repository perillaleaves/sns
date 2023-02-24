package project.comment.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

    private String content;
    private Long postId;

    public CommentRequest(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }
    
}