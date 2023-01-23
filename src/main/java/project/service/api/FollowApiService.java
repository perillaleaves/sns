package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.user.Follow;
import project.domain.user.User;
import project.domain.user.UserToken;
import project.exception.APIError;
import project.repository.FollowRepository;
import project.repository.TokenRepository;
import project.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowApiService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public void follow(Long userId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token).orElse(null);
        User fromUser = accessToken.getUser();
        User toUser = userRepository.findById(userId).orElse(null);

        if (fromUser.equals(toUser)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }

        Follow follow = Follow.follow(fromUser, toUser);
        fromUser.addFollowingSize(fromUser.getFollowingSize());
        toUser.addFollowerSize(toUser.getFollowerSize());
        followRepository.save(follow);
    }

    public void unfollow(Long followId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token).orElse(null);
        Follow follow = followRepository.findById(followId).orElse(null);
        if (!follow.getFromUser().equals(accessToken.getUser())) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }

        follow.getFromUser().removeFollowingSize(follow.getFromUser().getFollowingSize());
        follow.getToUser().removeFollowerSize(follow.getToUser().getFollowerSize());
        followRepository.delete(follow);
    }

}