package service;

import config.Configuration;
import domain.Member;
import domain.MemberDto;
import repository.MemberRepository;

import java.util.List;

public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService() {
        this.memberRepository = Configuration.memberRepository();
    }

    public Long saveMember(MemberDto dto) {
        Member member = new Member(dto.getName(), dto.getProfile());
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member findMemberById(Long id) {
        return memberRepository.findMemberById(id);
    }
}
