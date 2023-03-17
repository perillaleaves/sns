package project.follow.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.error.APIError;
import project.advice.exception.FollowNotFoundException;
import project.advice.exception.UserNotFoundException;
import project.follow.domain.Follow;
import project.follow.repository.FollowRepository;
import project.user.domain.User;
import project.user.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class FollowApiService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowApiService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public void follow(Long fromUserId, Long toUserId) {
        requestAndExistValidate(fromUserId, toUserId);
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(UserNotFoundException::new);
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(UserNotFoundException::new);

        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();
        fromUser.increaseFollowingSize(fromUser.getFollowingCount());
        toUser.increaseFollowerSize(toUser.getFollowerCount());
        followRepository.save(follow);
    }

    public void unfollow(Long fromUserId, Long toUserId) {
        Follow follow = followRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
                .orElseThrow(FollowNotFoundException::new);

        follow.getFromUser().decreaseFollowingSize(follow.getFromUser().getFollowingCount());
        follow.getToUser().decreaseFollowerSize(follow.getToUser().getFollowerCount());
        followRepository.delete(follow);
    }

    private void requestAndExistValidate(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }
        Optional<Follow> findFollow = followRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);
        if (findFollow.isPresent()) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }
    }

}