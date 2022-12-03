package client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApacheClient {
    private static String[] name = new String[]{"Kai", "Zion", "Jayden", "Eliana", "Luca", "Ezra", "Maeve", "Aaliyah"};
    private static String[] profiles = new String[]{"hello", "world", "this", "is", "load", "balancing", "project"};
    private static String[] uris = new String[]{"11", "22", "33", "44", "55", "66", "77", "88"};

    public static void main(String[] args) {
        repeatPost(1000);
        repeatGet(1000);
    }

    private static void repeatPost(int rep) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < rep; i++) {
            service.submit(() -> {
                try {
                    sendPost();
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        service.shutdown();
    }

    private static void repeatGet(int rep) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < rep; i++) {
            service.submit(() -> {
                try {
                    sendGet();
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        service.shutdown();
    }

    private static void sendPost() throws IOException, URISyntaxException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String uri = "http://127.0.0.1:8090/" + uris[random(uris.length)] + "/1";
            HttpUriRequest httpPost = RequestBuilder.post()
                    .setUri(new URI(uri))
                    .addParameter("name", name[random(name.length)])
                    .addParameter("profile", profiles[random(profiles.length)])
                    .build();

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
//                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

    private static void sendGet() throws URISyntaxException, IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String uri = "http://127.0.0.1:8090/" + uris[random(uris.length)] + "/list";
            HttpUriRequest httpGet = RequestBuilder.get()
                    .setUri(new URI(uri))
                    .build();
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

    private static int random(int bound) {
        return new Random().nextInt(bound);
    }
}
