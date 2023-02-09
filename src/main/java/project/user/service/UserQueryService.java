package project.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.UserNotFoundException;
import project.follow.repository.FollowRepository;
import project.post.domain.Post;
import project.user.response.UserPostListResponse;
import project.post.repository.PostRepository;
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
    private final PostRepository postRepository;
    private final FollowRepository followRepository;

    public UserQueryService(UserRepository userRepository, PostRepository postRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.followRepository = followRepository;
    }

    public ProfileResponse findUserProfile(Long userId, Long myId, Pageable pageable) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        UserDetailResponse userDetailResponse = new UserDetailResponse(
                findUser.getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + findUser.getUserProfileImage().getUserProfileImageURL(),
                findUser.getName(),
                findUser.getNickName(),
                findUser.getContent(),
                findUser.getPostSize(),
                findUser.getFollowerSize(),
                findUser.getFollowingSize(),
                userId.equals(myId),
                followRepository.existsFollowByFromUserIdAndToUserId(userId, myId));

        Slice<Post> posts = postRepository.findByUserId(userId, pageable);
        List<UserPostListResponse> postSlice = posts.stream()
                .map(p -> new UserPostListResponse(
                        p.getId(),
                        p.getPostImage().getId(),
                        "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + p.getPostImage().getPostImageUrl1()))
                .collect(Collectors.toList());

        return new ProfileResponse(userDetailResponse, postSlice, posts.hasNext());
    }

}