package handler;

import api.Gateway;
import api.MyServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.Configuration;

import java.io.IOException;

public class FrontServerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange arg) throws IOException {
        arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        Gateway gateway = Configuration.gateway();
        MyServer allocateServer = gateway.allocateServer();
        allocateServer.handle(arg);
    }
}
