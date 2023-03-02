package project.search.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchResponse {

    private SearchListResponse searchListResponse;

    public SearchResponse(SearchListResponse searchListResponse) {
        this.searchListResponse = searchListResponse;
    }

}