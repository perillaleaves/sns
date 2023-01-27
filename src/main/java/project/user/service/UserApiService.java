package project.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.advice.exception.APIError;
import project.advice.exception.AccessTokenNotFoundException;
import project.advice.exception.UserNotFoundException;
import project.common.EncryptUtils;
import project.common.GenerateToken;
import project.token.domain.UserToken;
import project.token.repository.TokenRepository;
import project.user.domain.User;
import project.user.repository.UserRepository;
import project.user.request.LoginRequest;
import project.user.request.ProfileEditRequest;
import project.user.request.SignupRequest;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserApiService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Transactional
    public void create(SignupRequest request) throws NoSuchAlgorithmException {
        validate(request);
        User user = User.builder()
                .userProfileImage(null)
                .email(request.getEmail())
                .name(request.getName())
                .nickName(request.getNickName())
                .password(EncryptUtils.encrypt(request.getPassword()))
                .postSize(0L)
                .followerSize(0L)
                .followingSize(0L)
                .build();
        userRepository.save(user);
    }

    @Transactional
    public String login(LoginRequest request) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equals(request.getEmail())) {
            throw new APIError("InconsistencyLoginId", "아이디가 일치하지 않습니다.");
        }
        if (!user.getPassword().equals(EncryptUtils.encrypt(request.getPassword()))) {
            throw new APIError("InconsistencyPassword", "비밀번호가 일치하지 않습니다.");
        }

        UserToken token = UserToken.builder()
                .user(user)
                .accessToken(GenerateToken.generatedToken(user, request.getEmail()))
                .build();
        tokenRepository.save(token);
        return token.getAccessToken();
    }

    @Transactional
    public void edit(Long userId, ProfileEditRequest request, String token) {
        editValidate(request);
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        if (!accessToken.getUser().equals(user)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
        }
        if (userRepository.existsUserByNickName(request.getNickName()) && !user.getNickName().equals(request.getNickName())) {
            throw new APIError("DuplicatedNickName", "중복된 닉네임입니다.");
        }

        user.editProfile(request);
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

    private void editValidate(ProfileEditRequest request) {
        if (2 > request.getUserName().length() || request.getUserName().length() > 10) {
            throw new APIError("InvalidName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (2 > request.getNickName().length() || request.getNickName().length() > 10) {
            throw new APIError("InvalidNickName", "닉네임을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (request.getContent().isEmpty() || request.getContent().length() > 150) {
            throw new APIError("InvalidContent", "소개를 150자 이하로 입력해주세요.");
        }
    }

}