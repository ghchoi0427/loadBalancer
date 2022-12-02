package domain;

import java.time.LocalDateTime;

public class ServerLog {
    String client;
    String host;
    String message;
    LocalDateTime createdTime;

    public ServerLog(String client, String host, String message) {
        this.client = client;
        this.host = host;
        this.message = message;
        this.createdTime = LocalDateTime.now();
    }

    public String getClient() {
        return client;
    }

    public String getHost() {
        return host;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
