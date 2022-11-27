package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import policy.LoadBalancingPolicy;
import util.RequestSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FrontServerHandler implements HttpHandler {
    private final String LOCAL_URL = "http://127.0.0.1:";

    RequestSender sender = new RequestSender();
    private final LoadBalancingPolicy loadBalancingPolicy;

    public FrontServerHandler(LoadBalancingPolicy loadBalancingPolicy) {
        this.loadBalancingPolicy = loadBalancingPolicy;
    }

    @Override
    public void handle(HttpExchange arg) throws IOException {
        arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        int gatewayPort = loadBalancingPolicy.gateway().getPort();
        String result = sender.send(LOCAL_URL + gatewayPort + "/api" + arg.getRequestURI(), arg);
        arg.sendResponseHeaders(200, result.length());
        OutputStream os = arg.getResponseBody();
        os.write(result.getBytes());
        os.close();
    }
}
