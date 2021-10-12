package com.xzacharb.coresvc.model.objects;

public class RoleObj {
    private String roleName;

    public RoleObj() {
    }

    public RoleObj(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
