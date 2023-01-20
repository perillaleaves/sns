package project.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.config.EncryptUtils;
import project.domain.member.Member;
import project.domain.member.UserToken;
import project.exception.APIError;
import project.repository.MemberRepository;
import project.repository.TokenRepository;
import project.request.LoginAndTokenRequest;
import project.request.SignupRequest;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberApiService {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

    public void create(SignupRequest request) {
        validate(request);
        Member member = Member.create(request);
        memberRepository.save(member);
    }

    public String login(LoginAndTokenRequest request) throws NoSuchAlgorithmException {
        Optional<Member> member = memberRepository.findByEmail(request.getEmail());
        if (member.isEmpty()) {
            throw new APIError("InconsistencyLoginId", "아이디가 일치하지 않습니다.");
        }
        if (!member.get().getPassword().equals(EncryptUtils.encrypt(request.getPassword()))) {
            throw new APIError("InconsistencyPassword", "비밀번호가 일치하지 않습니다.");
        }

        UserToken token = UserToken.create(member.get(), request.getEmail());
        tokenRepository.save(token);
        return token.getAccessToken();
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

        if (memberRepository.existsMemberByEmail(request.getEmail())) {
            throw new APIError("DuplicatedEmail", "중복된 이메일입니다.");
        }
        if (memberRepository.existsMemberByNickName(request.getNickName())) {
            throw new APIError("DuplicatedNickName" ,"중복된 닉네임입니다.");
        }

    }

}