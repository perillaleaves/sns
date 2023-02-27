package project.user.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.token.service.TokenApiService;
import project.user.request.LoginRequest;
import project.user.request.ProfileEditRequest;
import project.user.request.SignupRequest;
import project.user.response.LoginResponse;
import project.user.response.UserLoginResponse;
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
    public Response<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        UserLoginResponse login = userApiService.login(request);
        response.setHeader("token", login.getAccessToken());
        return new Response<>(new LoginResponse(login));
    }

    @PostMapping("/logout")
    public Response<ValidationResponse> logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        tokenService.deleteToken(token);
        return new Response<>(new ValidationResponse("Logout", "로그아웃"));
    }

    @PutMapping("/user/profile/{userId}")
    public Response<ValidationResponse> profileEdit(@PathVariable("userId") Long userId, @RequestBody ProfileEditRequest request, HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        userApiService.editProfile(userId, loginUserId, request);
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

    @PutMapping("/user/profileimage/{userId}")
    public Response<ValidationResponse> profileImageEdit(@PathVariable("userId") Long userId, @RequestParam(value = "image") MultipartFile file, HttpServletRequest httpServletRequest) throws IOException {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        userApiService.editProfileImage(userId, loginUserId, file, "profile");
        return new Response<>(new ValidationResponse("Update", "수정 완료"));
    }

}