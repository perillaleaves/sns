package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.post.dto.PostListDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponse {

    private UserDetailResponse userDetailResponse;
    private List<PostListDto> postListDto;

    public ProfileResponse(UserDetailResponse userDetailResponse, List<PostListDto> postListDto) {
        this.userDetailResponse = userDetailResponse;
        this.postListDto = postListDto;
    }
}