package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Post;
import project.domain.user.UserToken;
import project.exception.APIError;
import project.repository.PostRepository;
import project.repository.TokenRepository;
import project.request.PostRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostApiService {

    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public void create(HttpServletRequest httpServletRequest, PostRequest request) {
        validation(request);
        Post post = Post.create(httpServletRequest, request);
        postRepository.save(post);
    }

    public void update(Long postId, PostRequest request, HttpServletRequest httpServletRequest) {
        validation(request);
        String token = httpServletRequest.getHeader("token");
        Optional<UserToken> accessToken = tokenRepository.findByAccessToken(token);

        Optional<Post> findPost = postRepository.findById(postId);
        if (!findPost.get().getUser().equals(accessToken.get().getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }

        findPost.get().updatePostContent(request.getContent());
    }

    private static void validation(PostRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }


}