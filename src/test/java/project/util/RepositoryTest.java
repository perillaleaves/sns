package project.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.repository.UserRepository;

import java.security.NoSuchAlgorithmException;

import static project.common.EncryptUtils.encrypt;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    protected UserRepository userRepository;
    protected User user;

    @Autowired
    protected TokenRepository tokenRepository;
    protected UserToken userToken;

    protected UserProfileImage userProfileImage;

    @BeforeEach
    void inputData() throws NoSuchAlgorithmException {
        userProfileImage = UserProfileImage.builder()
                .userProfileImageURL("url")
                .build();

        user = User.builder()
                .userProfileImage(userProfileImage)
                .email("sweethome@gmail.com")
                .name("sweet")
                .nickName("home")
                .password(encrypt("123123"))
                .content("^^")
                .postSize(0L)
                .followingSize(0L)
                .followerSize(0L)
                .build();
        userRepository.save(user);

        userToken = UserToken.builder()
                .accessToken("123456789")
                .build();
        tokenRepository.save(userToken);
    }

    @AfterEach
    void cleanUp() {
        userRepository.delete(user);
        tokenRepository.delete(userToken);
    }

}