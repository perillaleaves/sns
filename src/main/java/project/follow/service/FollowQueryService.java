package project.follow.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.advice.exception.UserNotFoundException;
import project.follow.repository.FollowRepository;
import project.follow.repository.FollowRepositoryImpl;
import project.follow.response.follower.FollowerListResponse;
import project.follow.response.follower.FollowerUserListDetailResponse;
import project.follow.response.follower.FollowerUserListResponse;
import project.follow.response.following.FollowingListResponse;
import project.follow.response.following.FollowingUserListDetaiResponse;
import project.follow.response.following.FollowingUserListResponse;
import project.follow.response.following.UserIsFollowingResponse;
import project.user.domain.User;
import project.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowQueryService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final FollowRepositoryImpl followRepositoryImpl;
    private final String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";

    public FollowQueryService(UserRepository userRepository, FollowRepository followRepository, FollowRepositoryImpl followRepositoryImpl) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.followRepositoryImpl = followRepositoryImpl;
    }

    public FollowingListResponse findFollowingList(Long lastFollowId, Long userId, Long loginUserId, Pageable pageable) {
        User findUser = userRepository.findById(loginUserId)
                .orElseThrow(UserNotFoundException::new);
        UserIsFollowingResponse userIsFollowingResponse = new UserIsFollowingResponse(
                findUser.getId(),
                s3Url + findUser.getUserProfileImage().getUserProfileImageURL(),
                findUser.getName(),
                findUser.getNickName(),
                followRepository.existsFollowByFromUserIdAndToUserId(userId, loginUserId));

        List<FollowingUserListResponse> followingUserList = followRepositoryImpl.findFollowingUserList(lastFollowId, userId, loginUserId, pageable);
        List<FollowingUserListDetaiResponse> followingUserListDetail = followingUserList.stream()
                .map(f -> new FollowingUserListDetaiResponse(
                        f.getFollowId(),
                        f.getUserId(),
                        s3Url + f.getUserProfileImageUrl(),
                        f.getUserName(),
                        f.getNickName(),
                        followRepository.existsFollowByFromUserIdAndToUserId(loginUserId, f.getUserId())))
                .collect(Collectors.toList());

        boolean hasNext = false;
        if (followingUserList.size() >= pageable.getPageSize()) {
            hasNext = true;
        }

        return new FollowingListResponse(userIsFollowingResponse, followingUserListDetail, hasNext);
    }

    public FollowerListResponse findFollowerList(Long lastFollowId, Long userId, Long loginUserId, Pageable pageable) {
        User findUser = userRepository.findById(loginUserId)
                .orElseThrow(UserNotFoundException::new);
        UserIsFollowingResponse userIsFollowingResponse = new UserIsFollowingResponse(
                findUser.getId(),
                s3Url + findUser.getUserProfileImage().getUserProfileImageURL(),
                findUser.getName(),
                findUser.getNickName(),
                followRepository.existsFollowByFromUserIdAndToUserId(loginUserId, userId));

        List<FollowerUserListResponse> followerUserList = followRepositoryImpl.findFollowerUserList(lastFollowId, userId, loginUserId, pageable);
        List<FollowerUserListDetailResponse> followerUserListDetail = followerUserList.stream()
                .map(f -> new FollowerUserListDetailResponse(
                        f.getFollowId(),
                        f.getUserId(),
                        s3Url + f.getUserProfileImageUrl(),
                        f.getUserName(),
                        f.getNickName(),
                        followRepository.existsFollowByFromUserIdAndToUserId(loginUserId, f.getUserId())))
                .collect(Collectors.toList());

        boolean hasNext = false;
        if (followerUserList.size() >= pageable.getPageSize()) {
            hasNext = true;
        }

        return new FollowerListResponse(userIsFollowingResponse, followerUserListDetail, hasNext);
    }

}