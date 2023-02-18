package project.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.UserNotFoundException;
import project.follow.repository.FollowRepository;
import project.post.domain.PostImage;
import project.post.repository.PostImageRepository;
import project.post.repository.PostRepository;
import project.post.repository.PostRepositoryImpl;
import project.post.response.PostImagesResponse;
import project.post.response.ProfilePostDetailListResponse;
import project.post.response.ProfilePostListResponse;
import project.user.domain.User;
import project.user.repository.UserRepository;
import project.user.response.ProfileResponse;
import project.user.response.UserDetailResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;
    private final PostRepositoryImpl postRepositoryImpl;
    private final FollowRepository followRepository;
    private final String s3Url = "https://sweeethome.s3.ap-northeast-2.amazonaws.com/";
    private final PostImageRepository postImageRepository;

    public UserQueryService(UserRepository userRepository, PostRepositoryImpl postRepositoryImpl, FollowRepository followRepository,
                            PostImageRepository postImageRepository) {
        this.userRepository = userRepository;
        this.postRepositoryImpl = postRepositoryImpl;
        this.followRepository = followRepository;
        this.postImageRepository = postImageRepository;
    }

    public ProfileResponse findUserProfile(Long lastPostId, Long userId, Long loginUserId, Pageable pageable) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        UserDetailResponse userDetailResponse = new UserDetailResponse(
                findUser.getId(),
                s3Url + findUser.getUserProfileImage().getUserProfileImageURL(),
                findUser.getName(),
                findUser.getNickName(),
                findUser.getContent(),
                findUser.getPostSize(),
                findUser.getFollowerSize(),
                findUser.getFollowingSize(),
                userId.equals(loginUserId),
                followRepository.existsFollowByFromUserIdAndToUserId(userId, loginUserId));

        List<ProfilePostListResponse> postList = postRepositoryImpl.getProfilePostList(lastPostId, userId, pageable);
        List<ProfilePostDetailListResponse> postDetailResponse = postList.stream()
                .map(p -> {
                    List<PostImage> postImages = postImageRepository.findByPostId(p.getPostId());
                    List<PostImagesResponse> postImageList = postImages.stream()
                            .map(pi -> new PostImagesResponse(pi.getId(), pi.getPostImageUrl())).collect(Collectors.toList());

                    return new ProfilePostDetailListResponse(
                            p.getPostId(),
                            postImageList);
                }).collect(Collectors.toList());

        boolean hasNext = false;
        if (postDetailResponse.size() >= pageable.getPageSize()) {
            hasNext = true;
        }

        return new ProfileResponse(userDetailResponse, postDetailResponse, hasNext);
    }

}