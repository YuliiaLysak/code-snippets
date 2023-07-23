package edu.lysak.server;

public class Main {
    public static void main(String[] args) throws Exception {
        CustomHttpHandler customHttpHandler = new CustomHttpHandler();
        CustomAuthenticator customAuthenticator = new CustomAuthenticator("realm");
        CustomHttpServer customHttpServer = new CustomHttpServer(
            customHttpHandler,
            customAuthenticator
        );

        customHttpServer.createAndStart();
    }
}
