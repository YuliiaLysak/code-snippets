package edu.lysak.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CustomHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.printf("Accepted a %s request\n", exchange.getRequestMethod());

        if (exchange.getRequestMethod().equals("GET")) {
            handleGetRequest(exchange);
        } else if (exchange.getRequestMethod().equals("POST")) {
            handlePostRequest(exchange);
        }
        /* Other request types */
    }

    public Headers handleGetRequest(HttpExchange exchange) throws IOException {
        String response = "Hello, " +  exchange.getRequestURI().toString().split("=")[1];

        // Response code and length
        exchange.sendResponseHeaders(200, response.getBytes().length);
        // Set additional response header
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");

        try (OutputStream stream = exchange.getResponseBody()) {
            stream.write(response.getBytes());
        }

        return exchange.getResponseHeaders();
    }

    public Headers handlePostRequest(HttpExchange exchange) throws IOException {
        String response = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            while(reader.ready()) {
                response += reader.readLine();
            }
        }

        response = "Hello, " + response.split(":")[1].replaceAll("[^a-zA-Z]", "");

        // Prints "Content type: [application/json]"
        System.out.println("Content type: " + exchange.getRequestHeaders().get("Content-type"));

        exchange.sendResponseHeaders(200, response.length());

        try (OutputStream stream = exchange.getResponseBody()) {
            stream.write(response.getBytes());
        }

        return exchange.getResponseHeaders();
    }
}
