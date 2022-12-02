package api;

import com.sun.net.httpserver.HttpExchange;
import domain.Member;
import repository.MemberRepository;
import util.JsonParser;
import util.UriParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApiService {
    private final String uri;
    private final MemberRepository memberRepository;

    public ApiService(String uri, MemberRepository memberRepository) {
        this.uri = uri;
        this.memberRepository = memberRepository;
    }

    public String getUri() {
        return uri;
    }

    public void handle(HttpExchange arg) throws IOException {
        if (Objects.equals(arg.getRequestMethod(), "GET")) {
            doGet(arg);
        }

        if (Objects.equals(arg.getRequestMethod(), "POST")) {
            doPost(arg);
        }
    }

    private void doGet(HttpExchange arg) throws IOException {
        String message;
        String[] uris = UriParser.parseURI(arg.getRequestURI().toString());
        message = retrieveMember(uris);
        arg.sendResponseHeaders(200, message.length());
        arg.getResponseBody().write(message.getBytes(StandardCharsets.UTF_8));
        arg.getResponseBody().close();
    }

    private String retrieveMember(String[] uris) {
        String message;
        if (Objects.equals(uris[2], "list")) {
            message = JsonParser.objectToJson(memberRepository.findAll());
        } else {
            Member memberById = memberRepository.findMemberById(Long.valueOf(uris[2]));
            message = JsonParser.objectToJson(memberById);
        }
        return message;
    }

    private void doPost(HttpExchange arg) throws IOException {
        String message = "API Service [" + uri + "] :Do POST";
        System.out.println(message);

        InputStreamReader isr = new InputStreamReader(arg.getRequestBody());
        BufferedReader br = new BufferedReader(isr);
        saveMember(br);

        arg.sendResponseHeaders(200, message.length());
        arg.getResponseBody().write(message.getBytes(StandardCharsets.UTF_8));
        arg.getResponseBody().close();
    }

    private void saveMember(BufferedReader br) throws IOException {
        String body = br.readLine();
        Map<String, Object> parameters = new HashMap<>();
        UriParser.parseQuery(body, parameters);
        Member member = new Member((String) parameters.get("name"), (String) parameters.get("profile"));
        memberRepository.save(member);
    }
}
