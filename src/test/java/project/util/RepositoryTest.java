package project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import project.token.repository.TokenRepository;
import project.user.repository.UserRepository;

@SpringBootTest
@Repository
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class RepositoryTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

}