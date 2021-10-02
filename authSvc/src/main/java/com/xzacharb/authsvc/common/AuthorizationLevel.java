package com.xzacharb.authsvc.common;

public enum AuthorizationLevel {
    ROOT, ADMIN, USER;

    public byte byteValueOfLevel() {
        switch (this) {
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
