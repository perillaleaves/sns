package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Post;
import project.exception.APIError;
import project.repository.PostRepository;
import project.request.PostRequest;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class PostApiService {

    private final PostRepository postRepository;

    public void create(HttpServletRequest httpServletRequest, PostRequest request) {
        validation(request);
        Post post = Post.create(httpServletRequest, request);
        postRepository.save(post);
    }

    public void update(Long postId, PostRequest request) {
        validation(request);
        Post findPost = postRepository.findById(postId).orElse(null);
        findPost.updatePostContent(request.getContent());
    }

    private static void validation(PostRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > 300) {
            throw new APIError("InvalidContent", "문구를 1자이상 300자이하로 입력해주세요.");
        }
    }


}
