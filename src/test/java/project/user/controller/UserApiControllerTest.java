package project.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static project.util.Constants.*;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.common.EncryptUtils;
import project.common.response.Response;
import project.common.response.ValidationResponse;
import project.token.domain.UserToken;
import project.token.service.TokenApiService;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.request.SignupRequest;
import project.user.service.UserApiService;
import project.util.ControllerTest;

class UserApiControllerTest {

    private User user;
    private UserProfileImage userProfileImg;
    private UserToken userToken;

    @BeforeEach
    void inputData() throws NoSuchAlgorithmException {
        userProfileImg = UserProfileImage.builder()
                .id(USERPROFILEIMAGEID)
                .userProfileImageURL(USERPROFILEIMG)
                .build();

        User.UserBuilder builder = User.builder();
        builder.id(USERID);
        builder.userProfileImage(userProfileImg);
        builder.email(EMAIL);
        builder.name(USERNAME);
        builder.nickName(NICKNAME);
        builder.password(EncryptUtils.encrypt(PASSWORD));
        builder.content(CONTENT);
        builder.postSize(POSTSIZE);
        builder.followerSize(FOLLOEWRSIZE);
        builder.followingSize(FOLLOWINGSIZE);
        user = builder
                .build();

        userToken = UserToken.builder()
                .id(USERTOKENID)
                .accessToken(USERTOKEN)
                .user(user)
                .build();
    }

    @DisplayName("회원가입 - Controller")
    @Test
    void signUp() throws NoSuchAlgorithmException {
        SignupRequest request = new SignupRequest(EMAIL, USERNAME, NICKNAME, PASSWORD);
        UserApiService userApiService = mock(UserApiService.class);
        TokenApiService tokenApiService = mock(TokenApiService.class);
        doNothing().when(userApiService).signup(request);

        UserApiController userApiController = new UserApiController(userApiService, tokenApiService);

        Response<ValidationResponse> signupResponse = userApiController.signup(request);

    }

}