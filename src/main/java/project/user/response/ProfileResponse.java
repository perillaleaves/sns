package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;
import project.post.domain.Post;
import project.post.dto.PostListDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponse {

    private UserDetailResponse userDetailResponse;
    private List<PostListDto> postSlice;
    private boolean hasNext;

    public ProfileResponse(UserDetailResponse userDetailResponse, List<PostListDto> postSlice, boolean hasNext) {
        this.userDetailResponse = userDetailResponse;
        this.postSlice = postSlice;
        this.hasNext = hasNext;
    }
}