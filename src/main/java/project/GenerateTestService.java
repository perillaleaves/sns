package project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.post.request.PostRequest;
import project.post.service.PostApiService;
import project.token.repository.TokenRepository;
import project.user.service.UserApiService;

import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@RequiredArgsConstructor
public class GenerateTestService {

    private final UserApiService userApiService;
    private final PostApiService postApiService;
    private final TokenRepository tokenRepository;

    public void generate() {
//        try {
//            userApiService.create(SignupRequest.builder()
//                    .userProfileImageURL(null)
//                    .email("12345678@gmail.com")
//                    .name("깻잎")
//                    .nickName("perill")
//                    .password("123123").build());
//            for (int i = 1; i <= 12; i++) {
//                postApiService.create(PostRequest.builder()
//                        .userId(1L)
//                        .content("dsfasdfasd" + i)
//                        .build(), "70726F6A6563742E757365722E646F6D61696E2E5573657240316564316461313131303432383335313233343536373840676D61696C2E636F6D");
//            }
//        } catch (Error e) {
//            System.out.println("e = " + e);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
    }

}
