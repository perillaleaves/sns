package project.user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.common.EncryptUtils;
import project.token.domain.UserToken;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.request.SignupRequest;
import project.util.RepositoryTest;

import java.security.NoSuchAlgorithmException;
import static org.assertj.core.api.Assertions.*;
import static project.util.Constants.*;

class UserRepositoryTest extends RepositoryTest {

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
                .accessToken(USERTOKEN)
                .user(sweet)
                .build();
    }

    @Test
    @DisplayName("회원가입 Repository")
    void save() {
        userRepository.save(sweet);
    }

    @Test
    @DisplayName("특정 이메일을 가진 유저가 있는지 확인")
    void existsByEmail() {
        userRepository.save(sweet);

        assertThat(userRepository.existsUserByEmail(EMAIL)).isTrue();
    }

}