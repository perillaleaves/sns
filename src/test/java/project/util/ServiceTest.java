package project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import project.follow.repository.FollowRepository;
import project.post.repository.PostRepositoryImpl;
import project.s3.S3Service;
import project.token.repository.TokenRepository;
import project.token.service.TokenApiService;
import project.user.repository.UserProfileImageRepository;
import project.user.repository.UserRepository;
import project.user.service.UserApiService;
import project.user.service.UserQueryService;

@SpringBootTest
@Transactional
public class ServiceTest {

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected UserProfileImageRepository userProfileImageRepository;

    @MockBean
    protected TokenRepository tokenRepository;

    @MockBean
    protected FollowRepository followRepository;

    @MockBean
    protected PostRepositoryImpl postRepositoryImpl;

    @Autowired
    protected UserApiService userApiService;

    @Autowired
    protected UserQueryService userQueryService;

    @Autowired
    protected S3Service s3Service;

    @Autowired
    protected TokenApiService tokenApiService;

}