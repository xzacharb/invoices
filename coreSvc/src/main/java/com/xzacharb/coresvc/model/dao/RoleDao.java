package com.xzacharb.coresvc.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleDao {
    @Id
    @Column(length = 128)
    private String role_name;
}
