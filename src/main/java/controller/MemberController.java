package controller;

import domain.Member;
import service.MemberService;

public class MemberController implements Controller {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void doPost(Member member) {
        memberService.saveMember(member);
    }

    public Member doGet(Long id) {
        return memberService.findMemberById(id);
    }
}
