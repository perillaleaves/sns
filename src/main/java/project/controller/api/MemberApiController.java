package project.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.request.SignupRequest;
import project.response.ValidationResponse;
import project.service.MemberService;

@RestController
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ValidationResponse signup(@RequestBody SignupRequest request) {
        memberService.create(request);
        return new ValidationResponse("SignUp", "회원가입");
    }

}
