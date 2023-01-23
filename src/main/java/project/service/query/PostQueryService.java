package project.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.post.Post;
import project.repository.PostRepository;
import project.response.PostDetailResponse;
import project.response.PostImagesResponse;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostQueryService {

    private final PostRepository postRepository;

    public PostDetailResponse findPostDetail(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return new PostDetailResponse(post.getId(), post.getUser().getId(), post.getUser().getProfileImage(), post.getUser().getName(),
                post.getPostImages().stream().map(postImage -> new PostImagesResponse(postImage.getId(), postImage.getImageUrl())).collect(Collectors.toList()),
                post.getLikeSize(), post.getCommentSize(), post.getUpdatedAt());
    }

}