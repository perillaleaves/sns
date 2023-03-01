package project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import project.token.repository.TokenRepository;
import project.user.repository.UserRepository;
import project.user.service.UserApiService;
import project.user.service.UserQueryService;

@SpringBootTest
@Transactional
public class ServiceTest {

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected TokenRepository tokenRepository;

    @Autowired
    protected UserApiService userApiService;

    @Autowired
    protected UserQueryService userQueryService;

}