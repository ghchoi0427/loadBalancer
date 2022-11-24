package policy;

import api.gateway.GateWay;
import api.service.ApiService;

import java.util.List;

public class RoundRobin implements LoadBalancingPolicy{
    int gatewayIdx = 0;
    int serviceIdx = 0;

    @Override
    public GateWay gateway(List<GateWay> gateWays) {
        int size = gateWays.size();
        return gateWays.get(gatewayIdx++ % size);
    }

    @Override
    public ApiService apiService(List<ApiService> services) {
        int size = services.size();
        return services.get(serviceIdx++ % size);
    }
}
