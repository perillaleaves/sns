package project.token.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.advice.exception.AccessTokenNotFoundException;
import project.common.EncryptUtils;
import project.token.domain.UserToken;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.util.RepositoryTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static project.util.Constants.*;

class TokenRepositoryTest extends RepositoryTest {

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
    @DisplayName("토큰 삭제 확인")
    void deleteByAccessToken() {
        UserToken savedToken = tokenRepository.save(userToken);

        tokenRepository.deleteByAccessToken(savedToken.getAccessToken());

        assertThat(tokenRepository.findByAccessToken(savedToken.getAccessToken())).isEmpty();
    }

    @Test
    @DisplayName("저장된 토큰을 토큰 번호를 통해 확인할 수 있다")
    void findByAccessToken() {
        UserToken savedToken = tokenRepository.save(userToken);

        UserToken findToken = tokenRepository.findByAccessToken(savedToken.getAccessToken())
                .orElseThrow(AccessTokenNotFoundException::new);

        assertThat(findToken).isEqualTo(savedToken);
    }

}