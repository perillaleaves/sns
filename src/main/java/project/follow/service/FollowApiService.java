package project.follow.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.APIError;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.FollowNotFoundException;
import project.advice.exception.UserNotFoundException;
import project.follow.domain.Follow;
import project.follow.repository.FollowRepository;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;
import project.user.domain.User;
import project.user.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class FollowApiService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public FollowApiService(FollowRepository followRepository, UserRepository userRepository, TokenRepository tokenRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public void follow(Long userId, String token) {
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        User toUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        requestAndExistValidate(accessToken, toUser);

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

    private void requestAndExistValidate(UserToken accessToken, User toUser) {
        if (accessToken.getUser().equals(toUser)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }
        Optional<Follow> findFollow = followRepository.findByFromUserIdAndToUserId(accessToken.getUser().getId(), toUser.getId());
        if (findFollow.isPresent()) {
            throw new APIError("AlreadyExist", "이미 존재합니다");
        }
    }

}