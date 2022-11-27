package policy;

import api.gateway.GateWay;
import api.service.ApiService;

public interface LoadBalancingPolicy {

    GateWay gateway();

    ApiService apiService();
}
