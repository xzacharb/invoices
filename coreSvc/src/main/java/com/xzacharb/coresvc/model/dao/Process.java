package com.xzacharb.coresvc.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "processes")
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 127)
    private String name;
    @Column
    private String description;
    @Column(length = 127)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_start;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_end;

    public Process() {
    }

    public Process(long id, String name, String description, String status, Date date_start, Date date_end) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
}