package project.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.advice.exception.UserNotFoundException;
import project.common.EncryptUtils;
import project.user.domain.User;
import project.user.request.ProfileEditRequest;
import project.user.request.SignupRequest;
import project.util.ServiceTest;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static project.util.Constants.*;

class UserApiServiceTest extends ServiceTest {

    private User user;

    @BeforeEach
    void inputData() throws NoSuchAlgorithmException {
        user = User.builder()
                .email(EMAIL)
                .name(USERNAME)
                .nickName(NICKNAME)
                .password(EncryptUtils.encrypt(PASSWORD))
                .content(CONTENT)
                .postSize(POSTSIZE)
                .followerSize(FOLLOEWRSIZE)
                .followingSize(FOLLOWINGSIZE)
                .build();
    }

    @Test
    @DisplayName("회원가입 API - Service")
    void saveUser() throws NoSuchAlgorithmException {
        SignupRequest signupRequest = new SignupRequest(EMAIL, USERNAME, NICKNAME, PASSWORD);
        userApiService.signup(signupRequest);

        assertThat(signupRequest.getName()).isEqualTo(user.getName());
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
        userApiService.editProfile( findUserByEmail.getId(), findUserById.getId(), profileEditRequest);
        assertThat(userRepository.findByEmail(EMAIL).orElseThrow().getName()).isEqualTo("taegyun");
    }

}