package project.follow.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import project.follow.domain.Follow;
import project.follow.repository.FollowRepository;
import project.follow.repository.FollowRepositoryImpl;
import project.follow.response.FollowingListResponse;
import project.follow.response.FollowingUserListResponse;

@Service
public class FollowQueryService {

    private final FollowRepository followRepository;
    private final FollowRepositoryImpl followRepositoryImpl;

    public FollowQueryService(FollowRepository followRepository, FollowRepositoryImpl followRepositoryImpl) {
        this.followRepository = followRepository;
        this.followRepositoryImpl = followRepositoryImpl;
    }

    public FollowingListResponse findFollowingList(Long userId, Long myId, Pageable pageable) {
        Slice<FollowingUserListResponse> followingUserList = followRepositoryImpl.getFollowingUserList(userId, myId, pageable);

        return new FollowingListResponse(followingUserList);
    }

}