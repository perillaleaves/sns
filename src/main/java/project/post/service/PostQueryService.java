package project.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.PostNotFoundException;
import project.advice.exception.UserNotFoundException;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.post.response.PostDetailResponse;
import project.post.response.PostImagesResponse;
import project.postLike.reposiotry.PostLikeRepository;
import project.user.domain.User;
import project.user.repository.UserRepository;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;

    public PostQueryService(PostRepository postRepository, PostLikeRepository postLikeRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public PostDetailResponse findPostDetail(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        boolean isLike = postLikeRepository.existsPostLikeByPostIdAndUserId(post.getId(), userId);
        boolean isPostEdit = postRepository.existsPostByIdAndUserId(post.getId(), userId);

        return new PostDetailResponse(post.getId(), post.getUser().getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(),
                post.getPostImages().stream()
                        .map(postImage -> new PostImagesResponse(postImage.getId(),
                                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + postImage.getPostImageUrl())).collect(Collectors.toSet()),
                post.getContent(), isLike, post.getPostLikeSize(), post.getCommentSize(), isPostEdit, post.getUpdatedAt());
    }

}