package com.xzacharb.coresvc.model.dto;

import com.xzacharb.coresvc.model.dao.*;

import java.util.Date;

public class ManagementPerson {
    private long id;
    private String name;
    private String middle_name;
    private String sure_name;
    private String address;
    private String source;
    private Date date_start;
    private String managementType;
    private String role;
    private String cityObjDao;

    public ManagementPerson() {
    }

    public ManagementPerson(long id, String name, String middleName, String sureName, String address, String source, Date dateStart, String role, String cityObjDao, String managementType) {
        this.id = id;
        this.name = name;
        this.middle_name = middleName;
        this.sure_name = sureName;
        this.address = address;
        this.source = source;
        this.date_start = dateStart;
        this.role = role;
        this.cityObjDao = cityObjDao;
        this.managementType = managementType;
    }

    public ManagementPerson(ManagementPersonDao managementPersonDao) {
        this.id = managementPersonDao.getId();
        this.name = managementPersonDao.getName();
        this.middle_name = managementPersonDao.getMiddle_name();
        this.sure_name = managementPersonDao.getSure_name();
        this.address = managementPersonDao.getAddress();
        this.source = managementPersonDao.getSource();
        this.date_start = managementPersonDao.getDate_start();
        this.role = managementPersonDao.getRoleDao().getRole_name();
        this.cityObjDao = managementPersonDao.getCityObjDao().getCity_name();
        this.managementType = managementPersonDao.getManagementTypeDao().getType_name();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getSure_name() {
        return sure_name;
    }

    public String getAddress() {
        return address;
    }

    public String getSource() {
        return source;
    }

    public Date getDate_start() {
        return date_start;
    }

    public String getManagementType() {
        return managementType;
    }

    public String getRole() {
        return role;
    }

    public String getCityObjDao() {
        return cityObjDao;
    }
}
