package project.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.UserNotFoundException;
import project.token.domain.UserToken;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserApiServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() {
        UserProfileImage userProfileImage = UserProfileImage.builder()
                .userProfileImageURL("url")
                .build();

        User user = User.builder()
                .userProfileImage(userProfileImage)
                .email("abc@gmail.com")
                .name("sweetTest")
                .nickName("Sweet")
                .password("123123")
                .content("^^")
                .postSize(0L)
                .followingSize(0L)
                .followerSize(0L)
                .build();
        userRepository.save(user);
        User findUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);

        assertThat(findUser).isEqualTo(user);
    }

    @Test
    public void login() {
    }

    @Test
    public void edit() {
    }

}