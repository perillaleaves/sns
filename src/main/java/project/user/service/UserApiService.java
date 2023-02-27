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

@Service
public class UserApiService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final UserProfileImageRepository userProfileImageRepository;
    private final S3Service s3Service;
    private final String s3Url = "https://s3.ap-northeast-2.amazonaws.com/mullaepro.com/";

    public UserApiService(UserRepository userRepository, TokenRepository tokenRepository, UserProfileImageRepository userProfileImageRepository, S3Service s3Service) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.userProfileImageRepository = userProfileImageRepository;
        this.s3Service = s3Service;
    }

    @Transactional
    public void create(SignupRequest request, MultipartFile file, String dirName) throws NoSuchAlgorithmException, IOException {
        postBlankCheck(file);
        validate(request);

        String imgPaths = s3Service.upload(file, dirName);
        UserProfileImage userProfileImage = UserProfileImage.builder()
                .userProfileImageURL(imgPaths)
                .build();
        userProfileImageRepository.save(userProfileImage);

        User user = User.builder()
                .userProfileImage(userProfileImage)
                .email(request.getEmail())
                .name(request.getName())
                .nickName(request.getNickName())
                .password(EncryptUtils.encrypt(request.getPassword()))
                .content("")
                .postSize(0L)
                .followerSize(0L)
                .followingSize(0L)
                .build();
        userRepository.save(user);
    }

    @Transactional
    public UserLoginResponse login(LoginRequest request) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
        loginValidate(request, user);

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

    @Transactional
    public void edit(Long userId, Long loginUserId, ProfileEditRequest request) {
        editInputValidate(userId, loginUserId, request);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        editValidate(request, user);

        user.editProfile(request);
    }

    @Transactional
    public void editProfileImage(Long userId, Long loginUserId, MultipartFile file, String dirName) throws IOException {
        editProfileImageInputValidate(userId, loginUserId, file);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String imgPaths = s3Service.upload(file, dirName);
        user.getUserProfileImage().userProfileImageModify(imgPaths);
    }

    private void postBlankCheck(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new APIError("EmptyPostImage", "최소 1개 이상의 사진을 업로드 해주세요.");
        }
    }

    private void validate(SignupRequest request) {
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", request.getEmail());
        if (!email_validate) {
            throw new APIError("InvalidEmail", "이메일을 양식에 맞게 입력해주세요.");
        }

        if (2 > request.getName().length() || request.getName().length() > 10) {
            throw new APIError("InvalidName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (2 > request.getNickName().length() || request.getNickName().length() > 10) {
            throw new APIError("InvalidNickName", "닉네임을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (5 > request.getPassword().length() || request.getPassword().length() > 10) {
            throw new APIError("InvalidPassword", "패스워드를 5자 이상 10자 이하로 입력해주세요.");
        }

        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new APIError("DuplicatedEmail", "중복된 이메일입니다.");
        }
        if (userRepository.existsUserByNickName(request.getNickName())) {
            throw new APIError("DuplicatedNickName", "중복된 닉네임입니다.");
        }
    }

    private void loginValidate(LoginRequest request, User user) throws NoSuchAlgorithmException {
        if (!user.getPassword().equals(EncryptUtils.encrypt(request.getPassword()))) {
            throw new APIError("InconsistencyPassword", "비밀번호가 일치하지 않습니다.");
        }
    }

    private void editInputValidate(Long userId, Long loginUserId, ProfileEditRequest request) {
        if (2 > request.getUserName().length() || request.getUserName().length() > 10) {
            throw new APIError("InvalidName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (2 > request.getNickName().length() || request.getNickName().length() > 10) {
            throw new APIError("InvalidNickName", "닉네임을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (request.getContent().isEmpty() || request.getContent().length() > 150) {
            throw new APIError("InvalidContent", "소개를 150자 이하로 입력해주세요.");
        }
        if (!loginUserId.equals(userId)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }
    }

    private void editValidate(ProfileEditRequest request, User user) {
        if (userRepository.existsUserByNickName(request.getNickName()) && !user.getNickName().equals(request.getNickName())) {
            throw new APIError("DuplicatedNickName", "중복된 닉네임입니다.");
        }
    }

    private void editProfileImageInputValidate(Long userId, Long loginUserId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new APIError("EmptyPostImage", "최소 1개 이상의 사진을 업로드 해주세요.");
        }
        if (!loginUserId.equals(userId)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }
    }

}