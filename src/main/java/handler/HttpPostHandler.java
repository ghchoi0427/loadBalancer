package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import util.UriParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpPostHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange arg) throws IOException {
        System.out.println("request method = POST");
        arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
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
