package domain;

import java.time.LocalDateTime;

public class MemberDto {
    private String name;
    private String profile;
    private String createTime;

    public MemberDto(String name, String profile) {
        this.name = name;
        this.profile = profile;
        this.createTime = String.valueOf(LocalDateTime.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCreateTime() {
        return createTime;
    }
}
