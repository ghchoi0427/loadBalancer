package api;

import com.sun.net.httpserver.HttpExchange;
import config.Configuration;
import domain.ServerLog;
import domain.SocketConst;
import util.UriParser;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static util.DateTimeFormat.getFormattedDateTime;

public class MyServer {
    private final int port;
    private List<ServerLog> logs = new ArrayList<>();
    private Socket socket;

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
        try {
            this.socket = new Socket(SocketConst.ADDRESS, SocketConst.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        System.out.println("Server [" + port + "] : started");
    }

    public void handle(HttpExchange arg) throws IOException {
        sendLog(arg);
        String[] uri = UriParser.parseURI(arg.getRequestURI().toString());
        logs.add(new ServerLog("", String.valueOf(port), arg.getRequestMethod() + " : " + uri[2]));

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

    private void sendLog(HttpExchange arg) throws IOException {
        OutputStream out = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(out, true);
        String serverPort = String.valueOf(port);
        String message = "[" + getFormattedDateTime() + "]"
                + " SERVER [" + serverPort + "] : "
                + arg.getRequestMethod() + " /"
                + UriParser.parseURI(arg.getRequestURI().toString())[1];
        pw.println(message);
    }
}
