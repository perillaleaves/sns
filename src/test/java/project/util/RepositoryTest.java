package project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import project.token.repository.TokenRepository;
import project.user.repository.UserProfileImageRepository;
import project.user.repository.UserRepository;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserProfileImageRepository userProfileImageRepository;

    @Autowired
    protected TokenRepository tokenRepository;

}