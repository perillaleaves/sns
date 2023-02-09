package project.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponse {

    private UserDetailResponse userDetailResponse;
    private List<UserPostListResponse> postSlice;
    private boolean hasNext;

    public ProfileResponse(UserDetailResponse userDetailResponse, List<UserPostListResponse> postSlice, boolean hasNext) {
        this.userDetailResponse = userDetailResponse;
        this.postSlice = postSlice;
        this.hasNext = hasNext;
    }
}