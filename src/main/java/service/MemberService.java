package service;

import domain.Member;
import repository.MemberRepositoryImpl;

import java.util.List;

public class MemberService {
    private final MemberRepositoryImpl memberRepository;


    public MemberService(MemberRepositoryImpl memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long saveMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member findMemberById(Long id) {
        return memberRepository.findMemberById(id);
    }
}
