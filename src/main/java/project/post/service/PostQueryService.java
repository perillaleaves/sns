package project.post.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.PostNotFoundException;
import project.post.domain.Post;
import project.post.repository.PostRepository;
import project.post.repository.PostRepositoryImpl;
import project.post.response.PostDetailResponse;
import project.post.response.PostListDetailResponse;
import project.post.response.PostListResponse;
import project.post.response.NewsFeedListResponse;
import project.postLike.reposiotry.PostLikeRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostRepositoryImpl postRepositoryImpl;

    public PostQueryService(PostRepository postRepository, PostLikeRepository postLikeRepository, PostRepositoryImpl postRepositoryImpl) {
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.postRepositoryImpl = postRepositoryImpl;
    }

    private String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullae.com/";

    public PostDetailResponse findPostDetail(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return new PostDetailResponse(post.getId(),
                post.getUser().getId(),
                s3Url + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(),
                post.getContent(),
                postLikeRepository.existsPostLikeByPostIdAndUserId(post.getId(), userId),
                post.getPostLikeSize(),
                post.getCommentSize(),
                postRepository.existsPostByIdAndUserId(post.getId(), userId),
                post.getUpdatedAt(),
                s3Url + post.getPostImage().getPostImageUrl1(),
                s3Url + post.getPostImage().getPostImageUrl2(),
                s3Url + post.getPostImage().getPostImageUrl3(),
                s3Url + post.getPostImage().getPostImageUrl4(),
                s3Url + post.getPostImage().getPostImageUrl5(),
                s3Url + post.getPostImage().getPostImageUrl6(),
                s3Url + post.getPostImage().getPostImageUrl7(),
                s3Url + post.getPostImage().getPostImageUrl8(),
                s3Url + post.getPostImage().getPostImageUrl9(),
                s3Url + post.getPostImage().getPostImageUrl10());
    }

    public NewsFeedListResponse findPostList(Long userId, Pageable pageable) {
        Slice<PostListResponse> postList = postRepositoryImpl.getPostList(userId, pageable);
        List<PostListDetailResponse> postListDetail = postList.stream()
                .map(p -> new PostListDetailResponse(
                        p.getPostId(),
                        p.getUserId(),
                        s3Url + p.getUserProfileImageUrl(),
                        p.getUsername(),
                        p.getNickName(),
                        s3Url + p.getPostImageUrl1(),
                        s3Url + p.getPostImageUrl2(),
                        s3Url + p.getPostImageUrl3(),
                        s3Url + p.getPostImageUrl4(),
                        s3Url + p.getPostImageUrl5(),
                        s3Url + p.getPostImageUrl6(),
                        s3Url + p.getPostImageUrl7(),
                        s3Url + p.getPostImageUrl8(),
                        s3Url + p.getPostImageUrl9(),
                        s3Url + p.getPostImageUrl10(),
                        postLikeRepository.existsPostLikeByPostIdAndUserId(p.getPostId(), userId),
                        p.getContent(),
                        p.getCommentSize(),
                        p.getUpdatedAt()))
                .collect(Collectors.toList());

        return new NewsFeedListResponse(postListDetail, postList.hasNext());
    }

}