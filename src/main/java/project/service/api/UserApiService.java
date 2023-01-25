package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.config.EncryptUtils;
import project.domain.user.User;
import project.domain.user.UserToken;
import project.exception.APIError;
import project.exception.AccessTokenNotFoundException;
import project.exception.UserNotFoundException;
import project.repository.TokenRepository;
import project.repository.UserRepository;
import project.request.LoginAndTokenRequest;
import project.request.ProfileEditRequest;
import project.request.SignupRequest;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserApiService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public void create(SignupRequest request) {
        validate(request);
        User user = User.create(request);
        userRepository.save(user);
    }

    public String login(LoginAndTokenRequest request) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equals(request.getEmail())) {
            throw new APIError("InconsistencyLoginId", "아이디가 일치하지 않습니다.");
        }
        if (!user.getPassword().equals(EncryptUtils.encrypt(request.getPassword()))) {
            throw new APIError("InconsistencyPassword", "비밀번호가 일치하지 않습니다.");
        }

        UserToken token = UserToken.create(user, request.getEmail());
        tokenRepository.save(token);
        return token.getAccessToken();
    }

    public void edit(String nickName, ProfileEditRequest request, HttpServletRequest httpServletRequest) {
        editValidate(request);
        String token = httpServletRequest.getHeader("token");
        UserToken accessToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(AccessTokenNotFoundException::new);
        User user = userRepository.findByNickName(nickName)
                .orElseThrow(UserNotFoundException::new);

        if (userRepository.existsMemberByNickName(request.getNickName()) && !user.getNickName().equals(request.getNickName())) {
            throw new APIError("DuplicatedNickName", "중복된 닉네임입니다.");
        }
        if (!accessToken.getUser().equals(user)) {
            throw new APIError("NotRequest", "잘못된 요청입니다.");
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

        if (userRepository.existsMemberByEmail(request.getEmail())) {
            throw new APIError("DuplicatedEmail", "중복된 이메일입니다.");
        }
        if (userRepository.existsMemberByNickName(request.getNickName())) {
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