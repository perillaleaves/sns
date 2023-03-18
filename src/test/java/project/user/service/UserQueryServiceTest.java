package project.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.advice.exception.UserNotFoundException;
import project.common.EncryptUtils;
import project.token.domain.UserToken;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.response.UserDetailResponse;
import project.util.ServiceTest;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static project.util.Constants.*;

class UserQueryServiceTest extends ServiceTest {

    private User user;
    private UserProfileImage userProfileImg;
    private UserToken userToken;

    @BeforeEach
    void inputData() throws NoSuchAlgorithmException {
        userProfileImg = UserProfileImage.builder()
                .id(USERPROFILEIMAGEID)
                .userProfileImageURL(USERPROFILEIMG)
                .build();

        user = User.builder()
                .id(USERID)
                .userProfileImage(userProfileImg)
                .email(EMAIL)
                .name(USERNAME)
                .nickName(NICKNAME)
                .password(EncryptUtils.encrypt(PASSWORD))
                .content(CONTENT)
                .postSize(POSTSIZE)
                .followerSize(FOLLOEWRSIZE)
                .followingSize(FOLLOWINGSIZE)
                .build();

        userToken = UserToken.builder()
                .id(USERTOKENID)
                .accessToken(USERTOKEN)
                .user(user)
                .build();
    }

    @Test
    @DisplayName("User Profile 찾기 Query - Service")
    void findUserProfile() {
        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));
        User findUserById = userRepository.findById(user.getId())
                .orElseThrow(UserNotFoundException::new);

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.of(user));
        User findUserByEmail = userRepository.findByEmail(user.getEmail())
                .orElseThrow(UserNotFoundException::new);

        UserDetailResponse userDetailResponse = new UserDetailResponse(
                USERID,
                USERPROFILEIMG,
                USERNAME,
                NICKNAME,
                CONTENT,
                POSTSIZE,
                FOLLOEWRSIZE,
                FOLLOWINGSIZE,
                true,
                followRepository.isFollowExistsByFromUserIdAndToUserId(findUserById.getId(), findUserByEmail.getId()));


    }

}