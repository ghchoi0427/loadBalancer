package api;

import policy.LoadBalancingPolicy;

public class Gateway {
    private final LoadBalancingPolicy loadBalancingPolicy;

    public Gateway(LoadBalancingPolicy loadBalancingPolicy) {
        this.loadBalancingPolicy = loadBalancingPolicy;
    }

    public MyServer allocateServer() {
        return loadBalancingPolicy.myServer();
    }
}
