package project.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.basic.response.BasicDetailResponse;
import project.basic.response.BasicResponse;
import project.basic.service.BasicQueryService;
import project.common.response.Response;

@RestController
public class BasicQueryController {

    private final BasicQueryService basicQueryService;

    public BasicQueryController(BasicQueryService basicQueryService) {
        this.basicQueryService = basicQueryService;
    }

    @GetMapping("/start")
    public Response<BasicResponse> getBasicImage() {
        BasicDetailResponse basicItem = basicQueryService.getBasicItem();
        return new Response<>(new BasicResponse(basicItem));
    }

}