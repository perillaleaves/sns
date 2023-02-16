package project.search.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchRequest {

    private String nickName;

    public SearchRequest(String nickName) {
        this.nickName = nickName;
    }

}