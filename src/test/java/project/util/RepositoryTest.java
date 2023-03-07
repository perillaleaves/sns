package project.util;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.token.repository.TokenRepository;
import project.user.repository.UserProfileImageRepository;
import project.user.repository.UserRepository;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class RepositoryTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserProfileImageRepository userProfileImageRepository;

    @Autowired
    private TokenRepository tokenRepository;

}