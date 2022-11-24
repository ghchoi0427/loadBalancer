package policy;

import api.gateway.GateWay;
import api.service.ApiService;

import java.util.List;

public interface LoadBalancingPolicy {

    GateWay gateway(List<GateWay> gateWays);

    ApiService apiService(List<ApiService> services);
}
