package com.xzacharb.coresvc.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleDao {
    @Id
    @Column(length = 255)
    private String role_name;

    public RoleDao() {
    }

    public RoleDao(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
