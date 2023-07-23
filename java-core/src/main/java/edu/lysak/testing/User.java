package edu.lysak.testing;

public class User {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean hasStrongPassword() {
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }

    public boolean hasValidUsername() {
        return username != null && !username.isBlank();
    }

    public boolean isValid() {
        return hasValidUsername() && hasStrongPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
