package container;

import api.MyServer;

import java.util.HashMap;

public class MyServerContainer {
    private HashMap<Integer, MyServer> serverHashMap = new HashMap<>();
    private int increment = 0;

    public void addServer(MyServer myServer) {
        serverHashMap.put(increment++, myServer);
    }

    public HashMap<Integer, MyServer> getServerHashMap() {
        return serverHashMap;
    }
}
