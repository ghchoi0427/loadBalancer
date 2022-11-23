package domain;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String name;
    private String profile;
    private String createTime;

    public Member(Long id, String name, String profile) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        createTime = LocalDateTime.now().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
