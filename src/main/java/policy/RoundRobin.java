package policy;

import api.ApiService;
import api.MyServer;
import config.Configuration;
import container.ApiServiceContainer;
import container.MyServerContainer;

public class RoundRobin implements LoadBalancingPolicy {
    private final MyServerContainer serverContainer = Configuration.myServerContainer();
    private final ApiServiceContainer apiServiceContainer = Configuration.apiServiceContainer();
    private int increment = 0;

    @Override
    public MyServer myServer() {
        int size = serverContainer.getServerHashMap().size();
        return serverContainer.getServerHashMap().get(increment++ % size);
    }

    @Override
    public ApiService apiService() {
        return null;
    }
}
