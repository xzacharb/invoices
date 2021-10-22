package com.xzacharb.coresvc.model.data;

import com.xzacharb.coresvc.model.dao.*;

import javax.persistence.*;
import java.util.Date;

public class ManagementPerson {
    private final String name;
    private final String middle_name;
    private final String sure_name;
    private final String address;
    private final String source;
    private final Date date_start;
    private final String managementType;
    private final String role;
    private final String cityObjDao;

    public ManagementPerson( String name, String middleName, String sureName, String address, String source, Date dateStart, String role, String cityObjDao, String managementType) {
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
