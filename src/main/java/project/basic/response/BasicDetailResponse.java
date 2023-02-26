package project.basic.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicDetailResponse {

    private String Logo;
    private String basicImage;

    public BasicDetailResponse(String logo, String basicImage) {
        Logo = logo;
        this.basicImage = basicImage;
    }

}