import api.FrontServer;
import api.MyServer;
import config.Configuration;
import handler.FrontServerHandler;
import util.SocketServer;

import java.io.IOException;

public class ServerApplication {
    private static final int frontServerPort = 8090;
    private static final FrontServerHandler frontServerHandler = new FrontServerHandler();

    public static void run() throws IOException {
        SocketServer.start();
        Configuration.fillServerContainer();
        Configuration.fillApiServiceContainer();
        startFrontServer();
        startServer();
    }

    private static void startFrontServer() throws IOException {
        FrontServer frontServer = new FrontServer(frontServerPort, frontServerHandler);
        frontServer.initServer();
    }

    private static void startServer() {
        Configuration.myServerContainer().getServerHashMap().values().forEach(MyServer::start);
    }
}
