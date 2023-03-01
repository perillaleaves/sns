package project.user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.common.EncryptUtils;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.util.RepositoryTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;
import static project.util.Constants.*;

class UserRepositoryTest extends RepositoryTest {

    private User sweet;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        UserProfileImage userProfileImage = UserProfileImage.builder()
                .userProfileImageURL(USERPROFILEIMG)
                .build();

        sweet = User.builder()
                .userProfileImage(userProfileImage)
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
    @DisplayName("회원가입 API - Repository")
    void signup() {
        // given
        User savedUser = userRepository.save(sweet);
        // when

        // then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser).isEqualTo(sweet);
    }

}