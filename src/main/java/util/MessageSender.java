package util;

import log.Logger;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MessageSender implements Runnable {

    private final Socket from;


    public MessageSender(Socket from) {
        this.from = from;
    }


    @Override
    public void run() {
        while (true) {
            try {
                byte[] bytes = new byte[1024];
                int readBytes = from.getInputStream().read(bytes);
                if (readBytes > 0) {
                    String message = new String(bytes, 0, readBytes, StandardCharsets.UTF_8);
                    Logger.writeFile(message);
                    System.out.print(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
