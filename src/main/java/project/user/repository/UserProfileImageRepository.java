package project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.user.domain.UserProfileImage;

public interface UserProfileImageRepository extends JpaRepository<UserProfileImage, Long> {
}