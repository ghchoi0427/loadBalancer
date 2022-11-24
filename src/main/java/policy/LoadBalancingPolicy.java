package policy;

import api.gateway.GateWay;
import api.service.ApiService;

import java.util.List;

public interface LoadBalancingPolicy {

    GateWay gateway();

    ApiService apiService();
}
