package project.basic.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicResponse {

    private BasicDetailResponse basicDetailResponse;

    public BasicResponse(BasicDetailResponse basicDetailResponse) {
        this.basicDetailResponse = basicDetailResponse;
    }

}