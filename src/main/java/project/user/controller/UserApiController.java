package project.user.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.token.service.TokenApiService;
import project.user.request.LoginRequest;
import project.user.request.ProfileEditRequest;
import project.user.request.SignupRequest;
import project.user.service.UserApiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserApiController {

    private final UserApiService userApiService;
    private final TokenApiService tokenService;

    public UserApiController(UserApiService userApiService, TokenApiService tokenService) {
        this.userApiService = userApiService;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup")
    public Response<ValidationResponse> signup(@RequestParam(value = "image") MultipartFile images, SignupRequest request) throws NoSuchAlgorithmException, IOException {
        userApiService.create(request, images, "profile");
        return new Response<>(new ValidationResponse("SignUp", "회원가입"));
    }

    @PostMapping("/login")
    public Response<ValidationResponse> login(@RequestBody LoginRequest request, HttpServletResponse response, HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException {
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
    public Response<ValidationResponse> profileEdit(@PathVariable("userId") Long userId, @RequestParam(value = "image") MultipartFile file, ProfileEditRequest request, HttpServletRequest httpServletRequest) throws IOException {
        Long user = (Long) httpServletRequest.getAttribute("userId");
        userApiService.edit(userId, request, user, file, "profile");
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

}