package project.post.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.PostNotFoundException;
import project.post.domain.Post;
import project.post.domain.PostImage;
import project.post.repository.PostImageRepository;
import project.post.repository.PostRepository;
import project.post.repository.PostRepositoryImpl;
import project.post.response.*;
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
    private final PostImageRepository postImageRepository;
    private final String s3Url = "https://sweeethome.s3.ap-northeast-2.amazonaws.com/";

    public PostQueryService(PostRepository postRepository, PostLikeRepository postLikeRepository, PostRepositoryImpl postRepositoryImpl, PostImageRepository postImageRepository) {
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.postRepositoryImpl = postRepositoryImpl;
        this.postImageRepository = postImageRepository;
    }

    public PostDetailResponse findPostDetail(Long postId, Long loginUserId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        List<PostImage> postImages = postImageRepository.findByPostId(postId);
        List<PostImagesResponse> postImageUrls = postImages.stream()
                .map(p -> new PostImagesResponse(p.getId(), p.getPostImageUrl())).collect(Collectors.toList());


        return new PostDetailResponse(post.getId(),
                post.getUser().getId(),
                s3Url + post.getUser().getUserProfileImage().getUserProfileImageURL(),
                post.getUser().getNickName(),
                post.getContent(),
                postLikeRepository.existsPostLikeByPostIdAndUserId(post.getId(), loginUserId),
                post.getPostLikeSize(),
                post.getCommentSize(),
                postRepository.existsPostByIdAndUserId(post.getId(), loginUserId),
                post.getUpdatedAt(),
                postImageUrls);
    }

    public NewsFeedListResponse findPostList(Long lastPostId, Long loginUserId, Pageable pageable) {
        List<PostListResponse> postList = postRepositoryImpl.getPostList(lastPostId, pageable);

        List<PostListDetailResponse> postListDetail = postList.stream()
                .map(p -> {
                    List<PostImage> postImages = postImageRepository.findByPostId(p.getPostId());
                    List<PostImagesResponse> postImageList = postImages.stream()
                            .map(pi -> new PostImagesResponse(pi.getId(), pi.getPostImageUrl())).collect(Collectors.toList());

                    return new PostListDetailResponse(
                            p.getPostId(),
                            p.getUserId(),
                            s3Url + p.getUserProfileImageUrl(),
                            p.getUsername(),
                            p.getNickName(),
                            postImageList,
                            postLikeRepository.existsPostLikeByPostIdAndUserId(p.getPostId(), loginUserId),
                            p.getContent(),
                            p.getPostLikeSize(),
                            p.getCommentSize(),
                            postRepository.existsPostByIdAndUserId(p.getPostId(), loginUserId),
                            p.getUpdatedAt());

                }).collect(Collectors.toList());

        boolean hasNext = false;
        if (postList.size() >= pageable.getPageSize()) {
            hasNext = true;
        }

        return new NewsFeedListResponse(postListDetail, hasNext);
    }

}