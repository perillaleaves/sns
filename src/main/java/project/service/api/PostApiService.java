package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Post;
import project.domain.user.UserToken;
import project.exception.APIError;
import project.exception.AccessTokenNotFoundException;
import project.exception.PostNotFoundException;
import project.repository.PostRepository;
import project.repository.TokenRepository;
import project.request.PostRequest;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class PostApiService {

    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public void create(HttpServletRequest httpServletRequest, PostRequest request) {
        validation(request);
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);

        Post post = Post.create(httpServletRequest, request);
        accessToken.getUser().addPostSize(accessToken.getUser().getPostSize());
        postRepository.save(post);
    }

    public void update(Long postId, PostRequest request, HttpServletRequest httpServletRequest) {
        validation(request);
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        if (!post.getUser().equals(accessToken.getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }

        post.updatePostContent(request.getContent());
    }

    public void delete(Long postId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        if (!post.getUser().equals(accessToken.getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }

        accessToken.getUser().removePostSize(accessToken.getUser().getPostSize());
        postRepository.delete(post);
    }

    private static void validation(PostRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }


}