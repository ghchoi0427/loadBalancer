package controller;

import domain.Member;
import service.MemberService;
import util.JsonParser;

public class MemberController implements Controller {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void doPost(String memberJson) {
        Member member = (Member) JsonParser.jsonToObject(memberJson);
        memberService.saveMember(member);
    }

    public String doGet(Long id) {
        Member memberById = memberService.findMemberById(id);
        return JsonParser.objectToJson(memberById);
    }
}
