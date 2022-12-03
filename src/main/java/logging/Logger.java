package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String PATH = "log";

    public static void checkDirectory() {
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void writeFile(String content) throws IOException {
        checkDirectory();
        String fileName = PATH + "/log.txt";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(content);
        fileWriter.close();
    }
}
