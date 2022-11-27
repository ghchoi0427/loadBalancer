package container;

import api.service.ApiService;
import config.Configuration;

import java.util.HashMap;

public class ApiServiceContainer {
    HashMap<Integer, ApiService> container = new HashMap<>();
    private static ApiServiceContainer apiServiceContainer = new ApiServiceContainer();
    private static int sequence = 0;

    private ApiServiceContainer() {
    }

    public static ApiServiceContainer getInstance() {
        return apiServiceContainer;
    }

    public void addApiService() {
        ApiService apiService = new ApiService(sequence++, Configuration.memberService());
        System.out.println("api service " + apiService.getServiceId() + " created");
        container.put(apiService.getServiceId(), apiService);
    }

    public void addApiService(ApiService service) {
        container.put(sequence++, service);
    }

    public HashMap<Integer, ApiService> getContainer() {
        return container;
    }

    public void setContainer(HashMap<Integer, ApiService> container) {
        this.container = container;
    }
}
