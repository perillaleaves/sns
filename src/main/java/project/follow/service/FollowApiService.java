package project.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.follow.domain.Follow;
import project.token.domain.UserToken;
import project.common.exception.APIError;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.FollowNotFoundException;
import project.advice.exception.UserNotFoundException;
import project.follow.repository.FollowRepository;
import project.token.repository.TokenRepository;
import project.user.domain.User;
import project.user.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowApiService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public void follow(Long userId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        User toUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        if (accessToken.getUser().equals(toUser)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }
        Optional<Follow> findFollow = followRepository.findByFromUserIdAndToUserId(accessToken.getUser().getId(), toUser.getId());
        if (findFollow.isPresent()) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }

        Follow follow = Follow.builder()
                .fromUser(accessToken.getUser())
                .toUser(toUser)
                .build();
        accessToken.getUser().addFollowingSize(accessToken.getUser().getFollowingSize());
        toUser.addFollowerSize(toUser.getFollowerSize());
        followRepository.save(follow);
    }

    public void unfollow(String token, Long userId) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        Follow follow = followRepository.findByFromUserIdAndToUserId(accessToken.getUser().getId(), userId)
                .orElseThrow(FollowNotFoundException::new);

        follow.getFromUser().removeFollowingSize(follow.getFromUser().getFollowingSize());
        follow.getToUser().removeFollowerSize(follow.getToUser().getFollowerSize());
        followRepository.delete(follow);
    }

}