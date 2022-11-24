package util;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import config.Configuration;

public class ServerUtil {

    public static void initServer(HttpServer server, String path, HttpHandler handler) {
        Configuration.bindHandler(server, path, handler);
        server.setExecutor(null);
        System.out.println("server initiated");
    }
}
