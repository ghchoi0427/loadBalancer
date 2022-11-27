package api.service;

import com.sun.net.httpserver.HttpExchange;
import domain.Member;
import domain.MemberDto;
import service.MemberService;
import util.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApiService {
    private int serviceId;
    private String uriMapping;

    public ApiService(int serviceId, MemberService memberService) {
        this.serviceId = serviceId;
        this.memberService = memberService;
        uriMapping = "/" + serviceId;
    }

    private final MemberService memberService;

    public String queryGet(Map<String, Object> parameter, HttpExchange arg) throws IOException {
        System.out.println("arg.getRequestMethod() = " + arg.getRequestMethod());

        if (arg.getRequestMethod().equals("GET")) {
            System.out.println("api-service-"+serviceId+" : GET");
            return doGet();
        }

        return null;

    }

    public String queryPost(HttpExchange arg) {
        return null;
    }

    public void doPost(String memberJson) {
        MemberDto member = (MemberDto) JsonParser.jsonToObject(memberJson);
        System.out.println("member.getName() = " + member.getName());
        System.out.println("member.getProfile() = " + member.getProfile());
        memberService.saveMember(member);
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
