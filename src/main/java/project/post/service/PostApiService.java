package project.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.post.request.PostRequest;
import project.token.domain.UserToken;
import project.common.exception.APIError;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.token.repository.TokenRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PostApiService {

    private final PostRepository postRepository;
    private final TokenRepository tokenRepository;

    public void create(PostRequest request, String token) {
        validation(request);
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);

        Post post = Post.builder()
                .content(request.getContent())
                .commentSize(0L)
                .postLikeSize(0L)
                .user(accessToken.getUser())
                .build();
        accessToken.getUser().addPostSize(accessToken.getUser().getPostSize());
        postRepository.save(post);
    }

    public void update(Long postId, PostRequest request, String token) {
        validation(request);
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        if (!post.getUser().equals(accessToken.getUser())) {
            throw new APIError("NotLogin", "로그인 권한이 있는 유저의 요청이 아닙니다.");
        }

        post.updatePostContent(request.getContent());
    }

    public void delete(Long postId, String token) {
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