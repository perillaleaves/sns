package project.postLike.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.APIError;
import project.advice.exception.PostLikeNotFoundException;
import project.advice.exception.PostNotFoundException;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.postLike.domain.PostLike;
import project.postLike.reposiotry.PostLikeRepository;
import project.user.domain.User;

@Service
@Transactional
public class PostLikeApiService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public PostLikeApiService(PostLikeRepository postLikeRepository, PostRepository postRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
    }

    public void addLike(Long postId, User user) {
        existValidate(user, postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        PostLike postLike = PostLike.builder()
                .post(post)
                .user(user)
                .build();
        post.increasePostLikeSize(post.getPostLikeSize());
        postLikeRepository.save(postLike);
    }

    public void removeLike(Long postId, User user) {
        PostLike postLike = postLikeRepository.findByPostIdAndUserId(postId, user.getId())
                .orElseThrow(PostLikeNotFoundException::new);

        postLike.getPost().decreasePostLikeSize(postLike.getPost().getPostLikeSize());
        postLikeRepository.delete(postLike);
    }

    private void existValidate(User user, Long postId) {
        if (postLikeRepository.existsPostLikeByPostIdAndUserId(postId, user.getId())) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }
    }

}