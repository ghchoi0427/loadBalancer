import com.sun.net.httpserver.HttpServer;
import handler.HttpGetHandler;
import handler.HttpPostHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {
    public static void main(String[] args) {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/rg", new HttpGetHandler());
            server.createContext("/rp", new HttpPostHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("server started");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
