package edu.lysak.client;

import java.net.URI;
import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        CustomHttpClient customHttpClient = new CustomHttpClient(httpClient);

        // example 1
        URI firstWebSiteAddress = URI.create("http://info.cern.ch/hypertext/WWW/TheProject.html");
        customHttpClient.getRequest(firstWebSiteAddress);

        // example 2
        URI fakePostService = URI.create("https://jsonplaceholder.typicode.com/posts");
        customHttpClient.postRequest(fakePostService);

        // example 3
        customHttpClient.requestWithRetries(fakePostService);
    }
}
