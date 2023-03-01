package project.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.UserNotFoundException;
import project.common.EncryptUtils;
import project.token.domain.UserToken;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.repository.UserRepository;
import project.user.request.SignupRequest;
import project.util.ServiceTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static project.util.Constants.*;

class UserApiServiceTest extends ServiceTest {

    private User sweet;
    private UserProfileImage sweetProfileImg;
    private UserToken sweetToken;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        sweetProfileImg = UserProfileImage.builder()
                .userProfileImageURL(USERPROFILEIMG)
                .build();

        sweet = User.builder()
                .userProfileImage(sweetProfileImg)
                .email(EMAIL)
                .name(USERNAME)
                .nickName(NICKNAME)
                .password(EncryptUtils.encrypt(PASSWORD))
                .content(CONTENT)
                .postSize(POSTSIZE)
                .followerSize(FOLLOEWRSIZE)
                .followingSize(FOLLOWINGSIZE)
                .build();

        sweetToken = UserToken.builder()
                .accessToken("123123")
                .build();
    }

    @Test
    @DisplayName("회원가입 API - Service")
    public void saveUser() {
        User save = userRepository.save(sweet);

        SignupRequest signupRequest = new SignupRequest(EMAIL, USERNAME, NICKNAME, PASSWORD);
    }

    @Test
    public void login() {
    }

    @Test
    public void edit() {
    }

}