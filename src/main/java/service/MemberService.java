package service;

import domain.Member;
import repository.MemberRepositoryImpl;

import java.util.List;

public class MemberService {
    private final MemberRepositoryImpl memberRepository;


    public MemberService(MemberRepositoryImpl memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
