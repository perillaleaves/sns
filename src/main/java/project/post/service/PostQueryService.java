package project.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.PostNotFoundException;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.post.response.PostDetailResponse;
import project.postLike.reposiotry.PostLikeRepository;
import project.user.repository.UserRepository;

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

        return new PostDetailResponse(post.getId(),
                post.getUser().getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(),
                post.getContent(),
                isLike,
                post.getPostLikeSize(),
                post.getCommentSize(),
                isPostEdit,
                post.getUpdatedAt(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl1(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl2(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl3(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl4(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl5(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl6(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl7(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl8(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl9(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + post.getPostImage().getPostImageUrl10());
    }

}