package project.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import project.advice.exception.UserNotFoundException;
import project.common.EncryptUtils;
import project.token.domain.UserToken;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.request.LoginRequest;
import project.user.request.ProfileEditRequest;
import project.user.request.SignupRequest;
import project.util.ServiceTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static project.util.Constants.*;

class UserApiServiceTest extends ServiceTest {

    private User user;
    private UserProfileImage userProfileImg;
    private UserToken userToken;

    @BeforeEach
    void inputData() throws NoSuchAlgorithmException {
        userProfileImg = UserProfileImage.builder()
                .id(USERPROFILEIMAGEID)
                .userProfileImageURL(USERPROFILEIMG)
                .build();

        user = User.builder()
                .id(USERID)
                .userProfileImage(userProfileImg)
                .email(EMAIL)
                .name(USERNAME)
                .nickName(NICKNAME)
                .password(EncryptUtils.encrypt(PASSWORD))
                .content(CONTENT)
                .postSize(POSTSIZE)
                .followerSize(FOLLOEWRSIZE)
                .followingSize(FOLLOWINGSIZE)
                .build();

        userToken = UserToken.builder()
                .id(USERTOKENID)
                .accessToken(USERTOKEN)
                .user(user)
                .build();
    }

    @Test
    @DisplayName("회원가입 API - Service")
    void signup() throws NoSuchAlgorithmException {
        SignupRequest signupRequest = new SignupRequest(EMAIL, USERNAME, NICKNAME, PASSWORD);
        userApiService.signup(signupRequest);

        assertThat(signupRequest.getName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("로그인 API -Service")
    void login() throws NoSuchAlgorithmException {
        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.of(user));
        User findUserByEmail = userRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);

        given(userProfileImageRepository.findById(user.getUserProfileImage().getId()))
                .willReturn(Optional.of(userProfileImg));

        LoginRequest loginRequest = new LoginRequest(USERTOKEN, EMAIL, PASSWORD);
        userApiService.login(loginRequest);

        assertThat(loginRequest.getEmail()).isEqualTo(findUserByEmail.getEmail());
    }

    @Test
    @DisplayName("프로필 수정 API - Service")
    void editProfile() {
        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));
        User findUserById = userRepository.findById(user.getId())
                .orElseThrow(UserNotFoundException::new);

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.of(user));
        User findUserByEmail = userRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);

        ProfileEditRequest profileEditRequest = new ProfileEditRequest("taegyun", "gyunny", "lee");
        userApiService.updateUserProfile(findUserByEmail.getId(), findUserById.getId(), profileEditRequest);

        assertThat(userRepository.findByEmail(EMAIL).orElseThrow().getName()).isEqualTo("taegyun");
    }

    @Test
    @DisplayName("프로필 이미지 수정 API - Service")
    void editProfileImage() throws IOException {
        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));
        User findUserById = userRepository.findById(user.getId())
                .orElseThrow(UserNotFoundException::new);

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.of(user));
        User findUserByEmail = userRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);

        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpeg", "imgae/jpeg", new FileInputStream("/Users/taegyun/Downloads/cat1.jpeg"));
        String upload = s3Service.upload(mockMultipartFile, IMAMGEDIRNAME);
        findUserById.getUserProfileImage().updateUserProfileImageUrl(upload);

        userApiService.editProfileImage(findUserById.getId(), findUserByEmail.getId(), mockMultipartFile, IMAMGEDIRNAME);

        assertThat(findUserById.getUserProfileImage().getUserProfileImageURL()).isEqualTo(findUserByEmail.getUserProfileImage().getUserProfileImageURL());
    }

}