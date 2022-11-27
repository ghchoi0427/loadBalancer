package handler;

import api.service.ApiService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import policy.LoadBalancingPolicy;
import util.UriParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestHandler implements HttpHandler {
    private int id;
    private final LoadBalancingPolicy loadBalancingPolicy;

    public HttpRequestHandler(int id, LoadBalancingPolicy loadBalancingPolicy) {
        this.id = id;
        this.loadBalancingPolicy = loadBalancingPolicy;
    }

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
        System.out.println("request method: " + arg.getRequestMethod());
        arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        Map<String, Object> parameters = new HashMap<>();
        URI requestedUri = arg.getRequestURI();
        String query = requestedUri.getRawQuery();
        UriParser uriParser = new UriParser();

        uriParser.parseQuery(query, parameters);
        ApiService apiService = loadBalancingPolicy.apiService();
        String response = apiService.queryGet(parameters, arg);
        System.out.println("server-" + id + " response : " + response);
        arg.sendResponseHeaders(200, response.length());
        OutputStream os = arg.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handlePostMethod(HttpExchange arg) throws IOException {
        System.out.println("request method " + arg.getRequestMethod());
        Map<String, Object> parameters = new HashMap<>();

        InputStreamReader isr = new InputStreamReader(arg.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();

        String s = new String(arg.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("s = " + s);

        System.out.println("query = " + query);

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
