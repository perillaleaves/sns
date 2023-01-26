package project.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.request.LoginAndTokenRequest;
import project.request.ProfileEditRequest;
import project.request.SignupRequest;
import project.response.Response;
import project.response.ValidationResponse;
import project.service.api.TokenApiService;
import project.service.api.UserApiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserApiService userApiService;
    private final TokenApiService tokenService;

    @PostMapping("/signup")
    public Response<ValidationResponse> signup(@RequestBody SignupRequest request) {
        userApiService.create(request);
        return new Response<>(new ValidationResponse("SignUp", "회원가입"));
    }

    @PostMapping("/login")
    public Response<ValidationResponse> login(@RequestBody LoginAndTokenRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        String token = userApiService.login(request);
        response.setHeader("token", token);
        return new Response<>(new ValidationResponse("Login", "로그인"));
    }

    @PostMapping("/logout")
    public Response<ValidationResponse> logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        tokenService.deleteToken(token);
        return new Response<>(new ValidationResponse("Logout", "로그아웃"));
    }

    @PutMapping("/{nickName}")
    public Response<ValidationResponse> profileEdit(@PathVariable("nickName") String nickName, @RequestBody ProfileEditRequest request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        userApiService.edit(nickName, request, token);
        return new Response<>(new ValidationResponse("Fix", "수정 완료"));
    }

}