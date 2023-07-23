package edu.lysak.server;

import com.sun.net.httpserver.BasicAuthenticator;

public class CustomAuthenticator extends BasicAuthenticator {
    public CustomAuthenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals("admin") && password.equals("Java!1995");
    }
}
