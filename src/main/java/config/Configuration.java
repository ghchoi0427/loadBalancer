package config;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Configuration {

    public static void bindHandler(HttpServer server, String path, HttpHandler handler) {
        server.createContext(path, handler);
    }
}
