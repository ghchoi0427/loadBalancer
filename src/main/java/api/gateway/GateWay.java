package api.gateway;

import java.util.List;

public class GateWay {
    List<String> log;
    int port = 0;

    public void appendLog(String message) {
        log.add(message);
    }

    public List<String> getLog() {
        return log;
    }

    public GateWay(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
