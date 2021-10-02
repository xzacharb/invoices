package com.xzacharb.authsvc.model;

public class User {
    private String username;
    private String password;
    private String authorizationLevel;

    public User(String username, String password, String authorizationLevel) {
        this.username = username;
        this.password = password;
        this.authorizationLevel = authorizationLevel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthorizationLevel() {
        return authorizationLevel;
    }
}
