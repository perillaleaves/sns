package project.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.exception.APIError;
import project.request.SignupRequest;
import project.response.ErrorResponse;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.MemberService;

@RestController
public class MemberApiController {

    private final MemberService memberService;


    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public Response<ValidationResponse> signup(@RequestBody SignupRequest request) {
        try {
            memberService.signup(request);
            return new Response<>(new ValidationResponse("SignUp", "회원가입"));
        } catch (APIError e) {
            return new Response<>(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

}
