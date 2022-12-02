package container;

import api.ApiService;

import java.util.HashMap;

public class ApiServiceContainer {
    private HashMap<String, ApiService> apiMap = new HashMap<>();

    public void addApiService(ApiService apiService) {
        apiMap.put(apiService.getUri(), apiService);
    }

    public HashMap<String, ApiService> getApiMap() {
        return apiMap;
    }
}
