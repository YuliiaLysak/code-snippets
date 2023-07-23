package edu.lysak.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class CustomHttpServer {
    private final CustomHttpHandler customHttpHandler;
    private final CustomAuthenticator customAuthenticator;

    public CustomHttpServer(CustomHttpHandler customHttpHandler, CustomAuthenticator customAuthenticator) {
        this.customHttpHandler = customHttpHandler;
        this.customAuthenticator = customAuthenticator;
    }

    public void createAndStart() throws Exception {
        // Create HttpServer which is listening to the given port on the given IP address
        InetAddress localAddress = InetAddress.getByName("127.0.0.1");
        HttpServer server = HttpServer.create(new InetSocketAddress(localAddress, 8080), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));

        // Create a default context
        HttpContext context = server.createContext("/home");

        // Set the handler to process requests with the setter:
        context.setHandler(customHttpHandler);

        // Set basic auth
        context.setAuthenticator(customAuthenticator);

        // Start the server
        server.start();

        System.out.println("Server started");
    }
}
