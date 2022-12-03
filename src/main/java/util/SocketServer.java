package util;

import domain.SocketConst;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private static ServerSocket serverSocket;

    public SocketServer() {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(SocketConst.ADDRESS, SocketConst.PORT));
        Thread acceptThread = new Thread(() -> {
            try {
                acceptSockets();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        acceptThread.start();
    }

    private static void acceptSockets() throws IOException {
        while (true) {
            Socket accepted = serverSocket.accept();
            MessageSender sender = new MessageSender(accepted);
            Thread senderThread = new Thread(sender);
            senderThread.start();
        }
    }
}
