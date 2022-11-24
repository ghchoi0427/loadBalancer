package util;

import com.sun.net.httpserver.HttpServer;
import config.Configuration;
import handler.HttpRequestHandler;

public class ServerUtil {

    public static void initServer(HttpServer server) {
        Configuration.bindHandler(server, "/", new HttpRequestHandler());
        server.setExecutor(null);
        System.out.println("server initiated");
    }
}
