package container;

import api.service.ApiService;
import repository.MemberRepositoryImpl;
import service.MemberService;

import java.util.HashMap;

public class ApiServiceContainer {
    HashMap<Integer, ApiService> container = new HashMap<>();
    private static ApiServiceContainer apiServiceContainer;
    private static int sequence = 0;

    private ApiServiceContainer() {
    }

    public static ApiServiceContainer getInstance() {
        if (apiServiceContainer == null) {
            return new ApiServiceContainer();
        }
        return apiServiceContainer;
    }

    public void addApiService() {
        ApiService apiService = new ApiService(sequence++, new MemberService(new MemberRepositoryImpl()));
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
