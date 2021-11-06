package com.xzacharb.coresvc.impl.common;

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
    public static AuthorizationLevel levelOfByteValue(byte value) {
        switch (value) {
            case 0:
                return ROOT;
            case 1:
                return ADMIN;
            default:
                return USER;
        }
    }
}
