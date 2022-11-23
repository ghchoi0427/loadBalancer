package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Member;

import java.util.List;
import java.util.Map;

public class JsonParser {
    private static Gson gson = new Gson();

    public static Map<String, Object> jsonToMap(String jsonStr) {
        return gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static String mapMemberListToJson(List<Member> members) {
        return gson.toJson(members);
    }

    public static Member mapJsonStringToMember(String jsonStr) {
        return gson.fromJson(jsonStr, Member.class);
    }

    public static String mapMemberObjectToJsonString(Member member) {
        return gson.toJson(member);
    }

}
