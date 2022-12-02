package api;

import com.sun.net.httpserver.HttpExchange;
import config.Configuration;
import domain.ServerLog;
import util.UriParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int port;
    private List<ServerLog> logs = new ArrayList<>();

    public int getPort() {
        return port;
    }

    public List<ServerLog> getLog() {
        return logs;
    }

    public void addLog(ServerLog serverLog) {
        logs.add(serverLog);
    }

    public MyServer(int port) {
        this.port = port;
    }

    public void start() {
        System.out.println("Server [" + port + "] : started");
    }

    public void handle(HttpExchange arg) throws IOException {
        String[] uri = UriParser.parseURI(arg.getRequestURI().toString());
        logs.add(new ServerLog("", String.valueOf(port), arg.getRequestMethod() +" : "+ uri[2]));

        try {
            String apiUri = uri[1];
            ApiService service = Configuration.apiServiceContainer().getApiMap().get(apiUri);
            service.handle(arg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            arg.sendResponseHeaders(400, e.getMessage().length());
            arg.getResponseBody().write(e.getMessage().getBytes(StandardCharsets.UTF_8));
        }
    }
}
