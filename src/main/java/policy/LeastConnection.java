package policy;

import api.ApiService;
import api.MyServer;
import config.Configuration;
import container.MyServerContainer;
import domain.ServerLog;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LeastConnection implements LoadBalancingPolicy {
    private final MyServerContainer serverContainer = Configuration.myServerContainer();
    private int range = 500;

    @Override
    public MyServer myServer() {
        return serverContainer.getServerHashMap().values().stream().min((o1, o2) -> {
            int o1Count = getCurrentConnectionCount(o1, range);
            int o2Count = getCurrentConnectionCount(o2, range);
            if (o1Count < o2Count) {
                return 0;
            } else return 1;
        }).get();

    }

    @Override
    public ApiService apiService() {
        return null;
    }

    private int getCurrentConnectionCount(MyServer server, int range) {
        int result = 0;
        LocalDateTime now = LocalDateTime.now();
        for (ServerLog log : server.getLog()) {
            LocalDateTime from = log.getCreatedTime();
            long interval = ChronoUnit.MILLIS.between(from, now);
            if (interval < range) {
                result++;
            }
        }
        return result;
    }
}
