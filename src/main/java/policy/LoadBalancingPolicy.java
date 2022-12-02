package policy;

import api.ApiService;
import api.MyServer;

public interface LoadBalancingPolicy {
    MyServer myServer();

    ApiService apiService();
}
