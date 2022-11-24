package api.service;

import domain.Member;
import service.MemberService;
import util.JsonParser;

import java.util.List;

public class ApiService {
    private int serviceId;
    private String uriMapping;

    public ApiService(int serviceId, MemberService memberService) {
        this.serviceId = serviceId;
        this.memberService = memberService;
        uriMapping = "/" + serviceId;
    }

    private final MemberService memberService;

    public void doPost(String memberJson) {
        Member member = (Member) JsonParser.jsonToObject(memberJson);
        memberService.saveMember(member);
    }

    public String doGet(Long id) {
        Member memberById = memberService.findMemberById(id);
        return JsonParser.objectToJson(memberById);
    }

    public String doGet() {
        List<Member> allMembers = memberService.getAllMembers();
        return JsonParser.objectToJson(allMembers);
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getUriMapping() {
        return uriMapping;
    }

    public void setUriMapping(String uriMapping) {
        this.uriMapping = uriMapping;
    }
}
