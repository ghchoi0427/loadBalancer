import config.Configuration;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Configuration.init();
        ServerApplication.run();
    }
}
