package com.xzacharb.authsvc.common;

public abstract class AuthCommonMethods {
    public static byte valueOfAuthorizationLevel(AuthorizationLevel level) {
        switch (level) {
            case ROOT:
                return 0;
            case ADMIN:
                return 1;
            case USER:
            default:
                return 2;
        }
    }
}
