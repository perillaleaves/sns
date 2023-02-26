package project.basic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.basic.response.BasicDetailResponse;
import project.post.repository.PostImageRepository;

@Service
@Transactional(readOnly = true)
public class BasicQueryService {

    private final String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";

    public BasicDetailResponse getBasicItem() {
        BasicDetailResponse basicDetailResponse = new BasicDetailResponse(
                s3Url + "post/logo.png",
                s3Url + "post/default_profile (1).png"
        );
        return basicDetailResponse;
    }

}