package project.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListDto {

    private Long postImageId;
    private String postImageUrl;

    public PostListDto(Long postImageId, String postImageUrl) {
        this.postImageId = postImageId;
        this.postImageUrl = postImageUrl;
    }

}