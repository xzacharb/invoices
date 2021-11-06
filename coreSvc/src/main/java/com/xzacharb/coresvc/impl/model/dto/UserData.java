package com.xzacharb.coresvc.impl.model.dto;

import com.xzacharb.coresvc.impl.common.AuthorizationLevel;

public class UserData {
    private String token;
    private AuthorizationLevel level;

    public UserData() {
    }

    public UserData(String token) {
        this.token = token;
    }

    public UserData(String token, AuthorizationLevel level) {
        this.token = token;
        this.level = level;
    }

    public String getToken() {
        return token;
    }
    public AuthorizationLevel getLevel() {
        return level;
    }

}
