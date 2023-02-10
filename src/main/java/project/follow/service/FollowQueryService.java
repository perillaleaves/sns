package project.follow.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import project.advice.exception.UserNotFoundException;
import project.follow.repository.FollowRepository;
import project.follow.repository.FollowRepositoryImpl;
import project.follow.response.follower.FollowerListResponse;
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

    public FollowQueryService(UserRepository userRepository, FollowRepository followRepository, FollowRepositoryImpl followRepositoryImpl) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.followRepositoryImpl = followRepositoryImpl;
    }

    public FollowingListResponse findFollowingList(Long userId, Long myId, Pageable pageable) {
        User findUser = userRepository.findById(myId)
                .orElseThrow(UserNotFoundException::new);
        UserIsFollowingResponse userIsFollowingResponse = new UserIsFollowingResponse(
                findUser.getId(),
                "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + findUser.getUserProfileImage().getUserProfileImageURL(),
                findUser.getName(),
                findUser.getNickName(),
                followRepository.existsFollowByFromUserIdAndToUserId(myId, userId));

        Slice<FollowingUserListResponse> followingUserList = followRepositoryImpl.getFollowingUserList(userId, myId, pageable);
        List<FollowingUserListDetaiResponse> followingUserListDetail = followingUserList.stream()
                .map(f -> new FollowingUserListDetaiResponse(
                        f.getFollowId(),
                        f.getUserId(),
                        "https://s3.ap-northeast-2.amazonaws.com/mullae.com/" + f.getUserProfileImageUrl(),
                        f.getUserName(),
                        f.getNickName(),
                        followRepository.existsFollowByFromUserIdAndToUserId(myId, f.getUserId())))
                .collect(Collectors.toList());

        return new FollowingListResponse(userIsFollowingResponse, followingUserListDetail, followingUserList.hasNext());
    }

//    public FollowerListResponse findFollowerList(Long userId, Long myId, Pageable pageable) {
//
//    }

}