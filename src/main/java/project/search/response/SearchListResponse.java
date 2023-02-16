package project.search.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchListResponse {

    private List<SearchUserListDetailResponse> searchUserList;
    private boolean hasNext;

    public SearchListResponse(List<SearchUserListDetailResponse> searchUserList, boolean hasNext) {
        this.searchUserList = searchUserList;
        this.hasNext = hasNext;
    }

}