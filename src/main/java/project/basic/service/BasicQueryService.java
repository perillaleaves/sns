package project.basic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.basic.response.BasicDetailResponse;

@Service
@Transactional(readOnly = true)
public class BasicQueryService {

    private final String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";

    public BasicDetailResponse getBasicItem() {
        BasicDetailResponse basicDetailResponse = new BasicDetailResponse(
                s3Url + "logo/logo.png",
                s3Url + "logo/default_profile.png"
        );
        return basicDetailResponse;
    }

}