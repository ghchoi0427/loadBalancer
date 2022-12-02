package api;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class FrontServer {
    HttpServer server;
    private final int port;
    private final HttpHandler handler;

    public FrontServer(int port, HttpHandler handler) throws IOException {
        this.port = port;
        this.handler = handler;
    }

    public void initServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", handler);
        server.setExecutor(null);
        server.start();
        System.out.println("Server [FRONT] : started");
    }

    public void stopServer() {
        server.stop(0);
    }
}
