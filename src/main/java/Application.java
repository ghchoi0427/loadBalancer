import config.Configuration;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Configuration.init();
        Configuration.initApiService();
        ServerApplication.run();
    }
}
