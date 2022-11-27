import com.sun.net.httpserver.HttpServer;
import container.ServerContainer;
import handler.FrontServerHandler;
import policy.RoundRobin;
import util.ServerUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class ServerApplication {
    public static void run() throws IOException {
        startFrontServer();
        HashMap<Integer, HttpServer> serverMap = ServerContainer.getInstance().getServerInstances();
        for (int i = 0; i < serverMap.size(); i++) {
            serverMap.get(i).start();
            System.out.println("distributed server " + i + " started");
        }
    }

    private static void startFrontServer() throws IOException {
        HttpServer frontServer = HttpServer.create(new InetSocketAddress(8088), 0);
        ServerUtil.initServer(frontServer, "/", new FrontServerHandler(new RoundRobin()));
        frontServer.start();
        System.out.println("front server started");
    }


}
