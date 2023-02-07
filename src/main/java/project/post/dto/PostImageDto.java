package project.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImageDto {

    private Long postImageId;
    private String postImageUrl;

    public PostImageDto(Long postImageId, String postImageUrl) {
        this.postImageId = postImageId;
        this.postImageUrl = postImageUrl;
    }

}