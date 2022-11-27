import config.Configuration;
import util.TestSetting;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Configuration.init();
        Configuration.initApiService();
        TestSetting.fillDatabase();
        ServerApplication.run();
    }
}
