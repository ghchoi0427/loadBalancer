package config;

import api.ApiService;
import api.Gateway;
import api.MyServer;
import container.ApiServiceContainer;
import container.MyServerContainer;
import policy.RoundRobin;

public class Configuration {
    private static final MyServerContainer myServerContainer = new MyServerContainer();
    private static final ApiServiceContainer apiServiceContainer = new ApiServiceContainer();
    private static final Gateway gateway = new Gateway(new RoundRobin());
    private static int serverScale = 3;
    private static int apiServiceScale = 8;

    public static void fillServerContainer() {
        int port = 9000;
        for (int i = 0; i < serverScale; i++) {
            MyServer myServer = new MyServer(port + i);
            myServerContainer.addServer(myServer);
        }
    }

    public static void fillApiServiceContainer() {
        for (int i = 1; i <= apiServiceScale; i++) {
            ApiService apiService = new ApiService("" + i + i);
            apiServiceContainer.addApiService(apiService);
        }
        System.out.println("API Service: [/11] ~ [/" + apiServiceScale + apiServiceScale + "]");
    }

    public static MyServerContainer myServerContainer() {
        return myServerContainer;
    }

    public static ApiServiceContainer apiServiceContainer() {
        return apiServiceContainer;
    }

    public static Gateway gateway() {
        return gateway;
    }
}