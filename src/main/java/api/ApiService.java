package api;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ApiService {
    private final String uri;

    public ApiService(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void handle(HttpExchange arg) throws IOException {
        if (Objects.equals(arg.getRequestMethod(), "GET")) {
            doGet(arg);
        }

        if (Objects.equals(arg.getRequestMethod(), "POST")) {
            doPost(arg);
        }
    }

    private void doGet(HttpExchange arg) throws IOException {
        String message = "API Service [" + uri + "] :Do GET";
        System.out.println(message);
        arg.sendResponseHeaders(200, message.length());
        arg.getResponseBody().write(message.getBytes(StandardCharsets.UTF_8));
        arg.getResponseBody().close();
    }

    private void doPost(HttpExchange arg) throws IOException {
        String message = "API Service [" + uri + "] :Do POST";
        System.out.println(message);
        arg.sendResponseHeaders(200, message.length());
        arg.getResponseBody().write(message.getBytes(StandardCharsets.UTF_8));
        arg.getResponseBody().close();
    }
}
