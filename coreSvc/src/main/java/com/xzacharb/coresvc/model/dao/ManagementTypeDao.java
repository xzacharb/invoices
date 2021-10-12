package com.xzacharb.coresvc.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "management_types")
public class ManagementTypeDao {
    @Id
    @Column(length = 255)
    private String type_name;

    public ManagementTypeDao() {
    }

    public ManagementTypeDao(String type_name) {
        this.type_name = type_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
