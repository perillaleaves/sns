package project.follow.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import project.follow.domain.Follow;
import project.follow.repository.FollowRepository;
import project.follow.response.FollowingListResponse;
import project.follow.response.FollowingUserListResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowQueryService {

    private final FollowRepository followRepository;

    public FollowQueryService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public FollowingListResponse findFollowingList(Long userId, Long myId, Pageable pageable) {
        Slice<Follow> followingUsers = followRepository.findByToUserId(userId, myId, pageable);
        List<FollowingUserListResponse> followingUserListResponseList = followingUsers.stream()
                .map(f -> new FollowingUserListResponse(
                        f.getFromUser().getId(),
                        f.getFromUser().getUserProfileImage().getUserProfileImageURL(),
                        f.getFromUser().getName(),
                        f.getFromUser().getNickName(),
                        followRepository.existsFollowByFromUserIdAndToUserId(f.getFromUser().getId(), myId),
                        followRepository.existsFollowByFromUserIdAndToUserId(myId, userId)
                        ))
                .collect(Collectors.toList());

        return new FollowingListResponse(followingUserListResponseList);
    }

}