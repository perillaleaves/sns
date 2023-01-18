package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.member.Member;
import project.repository.MemberRepository;
import project.request.SignupRequest;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member signup(SignupRequest request) {
        Member member = Member.signup(request);
        return memberRepository.save(member);
    }

}
