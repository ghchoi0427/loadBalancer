import com.sun.net.httpserver.HttpServer;
import container.ServerContainer;

import java.util.HashMap;

public class ServerApplication {
    public static void run() {
        HashMap<Integer, HttpServer> serverMap = ServerContainer.getInstance().getServerInstances();
        for (int i = 0; i < serverMap.size(); i++) {
            serverMap.get(i).start();
            System.out.println("server " + i + " started");
        }
    }


}
