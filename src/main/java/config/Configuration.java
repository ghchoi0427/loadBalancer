package config;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import container.ServerContainer;
import handler.HttpRequestHandler;
import util.ServerUtil;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Configuration {

    public static void bindHandler(HttpServer server, String path, HttpHandler handler) {
        server.createContext(path, handler);
    }

    public static void init() throws IOException {
        HttpServer server1 = HttpServer.create(new InetSocketAddress(8081), 0);
        HttpServer server2 = HttpServer.create(new InetSocketAddress(8082), 0);
        HttpServer server3 = HttpServer.create(new InetSocketAddress(8083), 0);
        ServerUtil.initServer(server1, "/api", new HttpRequestHandler(1));
        ServerUtil.initServer(server2, "/api", new HttpRequestHandler(2));
        ServerUtil.initServer(server3, "/api", new HttpRequestHandler(3));
        ServerContainer.getInstance().addServerInstance(server1);
        ServerContainer.getInstance().addServerInstance(server2);
        ServerContainer.getInstance().addServerInstance(server3);
    }
}
