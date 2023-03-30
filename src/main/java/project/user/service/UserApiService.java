package project.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.advice.error.APIError;
import project.advice.exception.UserNotFoundException;
import project.common.EncryptUtils;
import project.common.GenerateToken;
import project.s3.S3Service;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;
import project.user.domain.User;
import project.user.domain.UserProfileImage;
import project.user.repository.UserProfileImageRepository;
import project.user.repository.UserRepository;
import project.user.request.LoginRequest;
import project.user.request.ProfileEditRequest;
import project.user.request.SignupRequest;
import project.user.response.UserLoginResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import static project.common.Constants.*;

@Service
@Transactional
public class UserApiService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final UserProfileImageRepository userProfileImageRepository;
    private final S3Service s3Service;

    public UserApiService(UserRepository userRepository, TokenRepository tokenRepository, UserProfileImageRepository userProfileImageRepository, S3Service s3Service) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.userProfileImageRepository = userProfileImageRepository;
        this.s3Service = s3Service;
    }

    public void signup(SignupRequest request) throws NoSuchAlgorithmException {
        validate(request);

        UserProfileImage userProfileImage = UserProfileImage.builder()
                .userProfileImageUrl("logo/default_profile.png")
                .build();
        userProfileImageRepository.save(userProfileImage);

        User user = User.builder()
                .userProfileImage(userProfileImage)
                .email(request.getEmail())
                .name(request.getUserName())
                .nickName(request.getNickName())
                .password(EncryptUtils.encrypt(request.getPassword()))
                .profileContent("")
                .postCount(0L)
                .followerCount(0L)
                .followingCount(0L)
                .build();
        userRepository.save(user);
    }

    public UserLoginResponse authenticateUser(LoginRequest request) throws NoSuchAlgorithmException {
        String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";
        User user = findUserByEmail(request);
        isConsistencyPassword(request, user);

        UserToken token = UserToken.builder()
                .user(user)
                .accessToken(GenerateToken.generatedToken(user, request.getEmail()))
                .build();
        tokenRepository.save(token);

        return new UserLoginResponse(user.getId(),
                s3Url + user.getUserProfileImage().getUserProfileImageURL(),
                user.getNickName(),
                token.getAccessToken());
    }

    public void updateUserProfile(Long userId, Long loginUserId, ProfileEditRequest request) {
        validateInput(userId, loginUserId, request);
        User user = findUserById(userId);
        validateUserProfileEdit(request, user);
        updateUserProfile(request, user);
    }

    public void editProfileImage(Long userId, Long loginUserId, MultipartFile file, String dirName) throws IOException {
        validateImageInput(userId, loginUserId, file);
        User user = findUserById(userId);
        String imageUrl = s3Service.upload(file, dirName);
        updateUserProfileImageUrl(user, imageUrl);
    }

    private void updateUserProfile(ProfileEditRequest request, User user) {
        user.editProfile(request);
    }

    private void updateUserProfileImageUrl(User user, String imageUrl) {
        user.getUserProfileImage().updateUserProfileImageUrl(imageUrl);
    }

    private String uploadUserProfileImage(MultipartFile file, String dirName) throws IOException {
        return s3Service.upload(file, dirName);
    }

    private void validate(SignupRequest request) {
        validateEmail(request.getEmail());
        validateUserName(request.getUserName());
        validateNickName(request.getNickName());
        validatePassword(request.getPassword());
        isEmailExists(request.getEmail());
        isNickNameExists(request.getNickName());
    }

    private void validateInput(Long userId, Long loginUserId, ProfileEditRequest request) {
        validateUserName(request.getUserName());
        validateNickName(request.getNickName());
        validateContent(request);
        validateLogin(userId, loginUserId);
    }

    private User findUserByEmail(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    private void isConsistencyPassword(LoginRequest request, User user) throws NoSuchAlgorithmException {
        if (!user.getPassword().equals(EncryptUtils.encrypt(request.getPassword()))) {
            throw new APIError("InconsistencyPassword", "비밀번호가 일치하지 않습니다.");
        }
    }

    private void validateUserProfileEdit(ProfileEditRequest request, User user) {
        if (userRepository.isNickNameExists(request.getNickName()) && !user.getNickName().equals(request.getNickName())) {
            throw new APIError("DuplicatedNickName", "중복된 닉네임입니다.");
        }
    }

    private void validateImageInput(Long userId, Long loginUserId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new APIError("EmptyPostImage", "최소 1개 이상의 사진을 업로드 해주세요.");
        }
        validateLogin(userId, loginUserId);
    }

    private void validateEmail(String email) {
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
        if (!email_validate) {
            throw new APIError("InvalidEmail", "이메일을 양식에 맞게 입력해주세요.");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            throw new APIError("InvalidPassword", "패스워드를 5자 이상 10자 이하로 입력해주세요.");
        }
    }

    private void validateUserName(String userName) {
        if (userName.length() < MIN_USERNAME_LENGTH || userName.length() > MAX_USERNAME_LENGTH) {
            throw new APIError("InvalidUserName", String.format("이름을 %d자 이상 %d자 이하로 입력해주세요.", MIN_NICKNAME_LENGTH, MAX_NICKNAME_LENGTH));
        }
    }

    private void validateNickName(String nickName) {
        if (nickName.length() < MIN_NICKNAME_LENGTH || nickName.length() > MAX_NICKNAME_LENGTH) {
            throw new APIError("InvalidNickName", String.format("이름을 %d자 이상 %d자 이하로 입력해주세요.", MIN_NICKNAME_LENGTH, MAX_NICKNAME_LENGTH));
        }
    }

    private void validateContent(ProfileEditRequest request) {
        if (request.getContent().isEmpty() || request.getContent().length() > MAX_CONTENT_LENGTH) {
            throw new APIError("InvalidContent", "소개를 150자 이하로 입력해주세요.");
        }
    }

    private void validateLogin(Long userId, Long loginUserId) {
        if (!loginUserId.equals(userId)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }
    }

    private void isEmailExists(String email) {
        if (userRepository.isEmailExists(email)) {
            throw new APIError("DuplicatedEmail", "이미 존재하는 이메일입니다..");
        }
    }

    private void isNickNameExists(String nickName) {
        if (userRepository.isNickNameExists(nickName)) {
            throw new APIError("DuplicatedNickName", "이미 존재하는 닉네임입니다.");
        }
    }

}