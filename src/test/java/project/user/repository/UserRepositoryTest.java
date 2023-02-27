package project.user.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.UserNotFoundException;
import project.user.domain.User;
import project.util.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void existsUserByEmail() {
        boolean exist = userRepository.existsUserByEmail(user.getEmail());

        assertThat(exist).isEqualTo(true);
    }

    @Test
    void existsUserByNickName() {
        boolean exist = userRepository.existsUserByNickName(user.getNickName());

        assertThat(exist).isEqualTo(true);
    }

    @Test
    void findByEmail() {
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new);

        assertThat(findUser).isEqualTo(user);
    }


}