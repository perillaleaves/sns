package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.member.Member;
import project.exception.APIError;
import project.repository.MemberRepository;
import project.request.SignupRequest;

import java.util.regex.Pattern;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(SignupRequest request) {
        validate(request);
        Member member = Member.create(request);
        return memberRepository.save(member);
    }

    private void validate(SignupRequest request) {
        boolean email_validate = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", request.getEmail());

        if (!email_validate) {
            throw new APIError("InvalidEmail", "이메일을 양식에 맞게 입력해주세요.");
        }
        if (2 > request.getName().length() && request.getName().length() > 10) {
            throw new APIError("InvalidName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (2 > request.getNickName().length() && request.getNickName().length() > 10) {
            throw new APIError("InvalidNickName", "이름을 2자 이상 10자 이하로 입력해주세요.");
        }
        if (5 > request.getPassword().length() && request.getPassword().length() > 10) {
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