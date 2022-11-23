import com.sun.net.httpserver.HttpServer;
import config.Configuration;
import handler.HttpRequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerApplication {
    public static void run() {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            Configuration.bindHandler(server, "/", new HttpRequestHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("server started");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
