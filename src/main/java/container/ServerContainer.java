package container;

import com.sun.net.httpserver.HttpServer;

import java.util.HashMap;

public class ServerContainer {
    private HashMap<Integer, HttpServer> serverInstances = new HashMap<>();
    private int index = 0;
    private static ServerContainer serverContainer = new ServerContainer();

    private ServerContainer() {

    }

    public static ServerContainer getInstance() {
        return serverContainer;
    }

    public  void addServerInstance(HttpServer server) {
        serverInstances.put(index++, server);
    }

    public  HashMap<Integer, HttpServer> getServerInstances() {
        return serverInstances;
    }

}
