package project.reComment.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReCommentRequest {

    private String content;

    public ReCommentRequest(String content) {
        this.content = content;
    }

}