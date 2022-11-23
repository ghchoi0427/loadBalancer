package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import util.UriParser;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange arg) throws IOException {
        arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (arg.getRequestMethod().equals("GET")) {
            handleGetMethod(arg);
        }
        if (arg.getRequestMethod().equals("POST")) {
            handlePostMethod(arg);
        }
    }

    private void handleGetMethod(HttpExchange arg) throws IOException {
        System.out.println("request method" + arg.getRequestMethod());
        arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        Map<String, Object> parameters = new HashMap<>();
        URI requestedUri = arg.getRequestURI();
        String query = requestedUri.getRawQuery();
        UriParser uriParser = new UriParser();
        uriParser.parseQuery(query, parameters);
        String response = "";
        for (String key : parameters.keySet()) {
            response += key + " = " + parameters.get(key) + "\n";
        }
        System.out.println("response : " + response);
        arg.sendResponseHeaders(200, response.length());
        OutputStream os = arg.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handlePostMethod(HttpExchange arg) throws IOException {
        System.out.println("request method" + arg.getRequestMethod());
        Map<String, Object> parameters = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(arg.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();

        UriParser uriParser = new UriParser();
        uriParser.parseQuery(query, parameters);

        String response = "";

        for (String key : parameters.keySet()) {
            response += key + "=" + parameters.get(key);
        }

        System.out.println("response = " + response);
        arg.sendResponseHeaders(200, response.length());
        OutputStream os = arg.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
