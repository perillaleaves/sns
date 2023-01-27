package project.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.token.service.TokenApiService;
import project.user.request.LoginRequest;
import project.user.request.ProfileEditRequest;
import project.user.request.SignupRequest;
import project.user.service.UserApiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserApiService userApiService;
    private final TokenApiService tokenService;

    @PostMapping("/signup")
    public Response<ValidationResponse> signup(@RequestBody SignupRequest request) throws NoSuchAlgorithmException {
        userApiService.create(request);
        return new Response<>(new ValidationResponse("SignUp", "회원가입"));
    }

    @PostMapping("/login")
    public Response<ValidationResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        String token = userApiService.login(request);
        response.setHeader("token", token);
        return new Response<>(new ValidationResponse("Login", "로그인"));
    }

    @PostMapping("/logout")
    public Response<ValidationResponse> logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        tokenService.deleteToken(token);
        return new Response<>(new ValidationResponse("Logout", "로그아웃"));
    }

    @PutMapping("/user/{userId}")
    public Response<ValidationResponse> profileEdit(@PathVariable("userId") Long userId, @RequestBody ProfileEditRequest request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        userApiService.edit(userId, request, token);
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

}