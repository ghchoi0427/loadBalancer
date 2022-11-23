package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import util.UriParser;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpGetHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange arg) throws IOException {
        System.out.println("request method Get");
        arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        System.out.println("arg.getRequestMethod() = " + arg.getRequestMethod());
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
}
