package project.controller.api;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.request.LoginAndTokenRequest;
import project.request.LogoutAndTokenRequest;
import project.request.SignupRequest;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.api.MemberService;
import project.service.api.TokenService;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final TokenService tokenService;

    // 1. 회원가입
    @PostMapping("/signup")
    public Response<ValidationResponse> signup(@RequestBody SignupRequest request) {
        memberService.create(request);
        return new Response<>(new ValidationResponse("SignUp", "회원가입"));
    }

    // 2. 로그인
    @PostMapping("/login")
    public Response<ValidationResponse> login(@RequestBody LoginAndTokenRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        String token = memberService.login(request);
        response.setHeader("token", token);
        return new Response<>(new ValidationResponse("Login", "로그인"));
    }

    // 3. 로그아웃
    @PostMapping("/logout")
    public Response<ValidationResponse> logout(@RequestBody LogoutAndTokenRequest request) {
        tokenService.deleteToken(request);
        return new Response<>(new ValidationResponse("Logout", "로그아웃"));
    }
}
