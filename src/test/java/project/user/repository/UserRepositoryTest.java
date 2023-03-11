package project.user.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.advice.exception.UserNotFoundException;
import project.common.EncryptUtils;
import project.token.domain.UserToken;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.util.RepositoryTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static project.util.Constants.*;
import static project.util.Constants.EMAIL;

class UserRepositoryTest extends RepositoryTest{

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
    @DisplayName("특정 이메일을 가진 유저가 있는지 확인")
    void existsByEmail() {
        userRepository.save(user);

        assertThat(userRepository.existsUserByEmail(EMAIL)).isTrue();
    }

    @Test
    @DisplayName("특정 닉네임을 가진 유저가 있는지 확인")
    void existsByNickName() {
        userRepository.save(user);

        assertThat(userRepository.existsUserByNickName(NICKNAME)).isTrue();
    }

    @Test
    @DisplayName("저장된 유저를 이메일을 통해 확인할 수 있다.")
    void findByEmail() {
        User savedUser = userRepository.save(user);

        User findUser = userRepository.findByEmail(EMAIL)
                .orElseThrow(UserNotFoundException::new);

        assertThat(findUser).isEqualTo(savedUser);
    }

}