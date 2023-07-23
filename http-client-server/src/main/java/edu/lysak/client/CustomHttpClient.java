package edu.lysak.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CustomHttpClient {
    private static final int MAX_TIMEOUT_MS = 10000;  // 10000 ms = 10 seconds
    private static final int MAX_RETRIES = 5;
    private static final int WAIT_TIMEOUT_MS = 5000;

    private final HttpClient httpClient;

    public CustomHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void getRequest(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET() // we can skip it, because the builder creates GET query by default
            .timeout(Duration.ofMillis(10000L))
            .build();

        try {
            HttpResponse<String> response = httpClient.send(
                request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode()); // 200 if everything is OK
            System.out.println(response.body());       // a long HTML text
        } catch (Exception e) {
            System.out.println("We cannot access the site. Please, try later.");
        }
    }

    public void postRequest(URI uri) {
        String bookData = "{\"title\":\"The Invisible Man\", \"author\":\"H. G. Wells\"}";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Content-Type", "application/json") // we specify that we send a JSON
            .POST(HttpRequest.BodyPublishers.ofString(bookData))
            .build();

        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode()); // 201 if everything is OK
            System.out.println(response.body());       // a JSON response with ID
        } catch (Exception e) {
            System.out.println("We cannot send data. Please, try later.");
        }
    }

    public void requestWithRetries(URI uri) {
        String payment = "{\"order\":\"1234\", \"price\":\"10000\"}";

        try {
            HttpResponse<String> response = sendDataWithRetries(
                uri,
                payment
            );
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("It's impossible to complete the action after several retries");
        }
    }

    private HttpResponse<String> sendDataWithRetries(URI service, String jsonData) throws Exception {
        int retry = 0;
        while (retry < MAX_RETRIES) {
            retry++;
            try {
                HttpResponse<String> response = sendData(service, jsonData);
                if (response.statusCode() < 400) {
                    return response;
                }
                if (response.statusCode() >= 500) {
                    System.out.println("A server error has occurred, will retry the request later");
                }
                throw new Exception("Incorrect request, probably, we need to fix the code");
            } catch (Exception e) {
                System.out.println("An interaction error has occurred, will retry the request later");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(WAIT_TIMEOUT_MS); // waiting before the next retry
            } catch (InterruptedException ignored) {
            }
        }
        throw new Exception("Cannot get the response after " + retry + " retries");
    }

    private HttpResponse<String> sendData(URI service, String jsonData) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(service)
            .timeout(Duration.ofMillis(MAX_TIMEOUT_MS))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonData))
            .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
