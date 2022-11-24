package policy;

import api.gateway.GateWay;
import api.service.ApiService;
import com.sun.net.httpserver.HttpServer;
import container.ServerContainer;

import java.util.HashMap;

public class RoundRobin implements LoadBalancingPolicy{
    int gatewayIdx = 0;
    int serviceIdx = 0;

    @Override
    public GateWay gateway() {
        HashMap<Integer, HttpServer> serverInstances = ServerContainer.getInstance().getServerInstances();
        HttpServer server = serverInstances.get(gatewayIdx++ % serverInstances.size());
        int port = server.getAddress().getPort();
        return new GateWay(port);
    }

    @Override
    public ApiService apiService() {
        return null;
    }
}
