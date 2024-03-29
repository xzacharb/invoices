package com.xzacharb.coresvc.impl.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "person_roles")
public final class Role {
    @Id
    @Column(length = 255)
    private String role_name;

    public Role() {
    }

    public Role(String roleName) {
        this.role_name = roleName;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
