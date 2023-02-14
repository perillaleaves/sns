package project.post.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfilePostResponse {

    List<ProfilePostListResponse> profilePostListResponses;

    public ProfilePostResponse(List<ProfilePostListResponse> profilePostListResponses) {
        this.profilePostListResponses = profilePostListResponses;
    }

}