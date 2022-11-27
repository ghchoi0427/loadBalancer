package util;

import config.Configuration;
import domain.MemberDto;
import service.MemberService;

import java.util.UUID;

public class TestSetting {

    public static void fillDatabase() {
        MemberService memberService = Configuration.memberService();
        for (int i = 0; i < 100; i++) {
            memberService.saveMember(new MemberDto(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        }
    }
}
