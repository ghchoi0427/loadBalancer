package config;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import container.ApiServiceContainer;
import container.ServerContainer;
import handler.HttpRequestHandler;
import policy.RoundRobin;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;
import service.MemberService;
import util.ServerUtil;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Configuration {
    private static MemberService memberService;
    private static MemberRepository memberRepository;

    public static MemberService memberService() {
        if (memberService == null) {
            return new MemberService();
        }
        return memberService;
    }

    public static MemberRepository memberRepository() {
        if (memberRepository == null) {
            return new MemberRepositoryImpl();
        }
        return memberRepository;
    }

    public static void bindHandler(HttpServer server, String path, HttpHandler handler) {
        server.createContext(path, handler);
    }

    public static void init() throws IOException {
        HttpServer server1 = HttpServer.create(new InetSocketAddress(8081), 0);
        HttpServer server2 = HttpServer.create(new InetSocketAddress(8082), 0);
        HttpServer server3 = HttpServer.create(new InetSocketAddress(8083), 0);
        ServerUtil.initServer(server1, "/api", new HttpRequestHandler(1, new RoundRobin()));
        ServerUtil.initServer(server2, "/api", new HttpRequestHandler(2, new RoundRobin()));
        ServerUtil.initServer(server3, "/api", new HttpRequestHandler(3, new RoundRobin()));
        ServerContainer.getInstance().addServerInstance(server1);
        ServerContainer.getInstance().addServerInstance(server2);
        ServerContainer.getInstance().addServerInstance(server3);
    }

    public static void initApiService() {
        for (int i = 0; i < 30; i++) {
            ApiServiceContainer.getInstance().addApiService();
        }
    }
}
