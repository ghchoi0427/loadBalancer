package api.gateway;

public class GateWay {
    int port = 0;

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
