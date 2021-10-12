package com.xzacharb.coresvc.model.objects;

import com.xzacharb.coresvc.model.dao.ManagementTypeDao;

import java.util.Date;

public class ManagementPersonObj {
    private String id;
    private String name;
    private String middleName;
    private String sureName;
    private String address;
    private String source;
    private Date dateStart;
    private ManagementTypeDao managementTypeDao;
    private RoleObj roleObj;
    private ContractorObj contractorObj;
    private CityObj cityObj;

    public ManagementPersonObj() {
    }

    public ManagementPersonObj(String name, String middleName, String address, String source, Date dateStart, RoleObj roleObj, ContractorObj contractorObj, CityObj cityObj, ManagementTypeDao managementTypeDao) {
        this.name = name;
        this.middleName = middleName;
        this.address = address;
        this.source = source;
        this.dateStart = dateStart;
        this.roleObj = roleObj;
        this.contractorObj = contractorObj;
        this.cityObj = cityObj;
        this.managementTypeDao = managementTypeDao;
    }

    public ManagementPersonObj(String name, String middleName, String sureName, String address, String source, Date dateStart) {
        this.name = name;
        this.middleName = middleName;
        this.sureName = sureName;
        this.address = address;
        this.source = source;
        this.dateStart = dateStart;
    }

    public ManagementPersonObj(String id, String name, String middleName, String sureName, String address, String source, Date dateStart, RoleObj roleObj, ContractorObj contractorObj, CityObj cityObj, ManagementTypeDao managementTypeDao) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.address = address;
        this.sureName = sureName;
        this.source = source;
        this.dateStart = dateStart;
        this.roleObj = roleObj;
        this.contractorObj = contractorObj;
        this.cityObj = cityObj;
        this.managementTypeDao = managementTypeDao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public RoleObj getRoleObj() {
        return roleObj;
    }

    public void setRoleObj(RoleObj roleObj) {
        this.roleObj = roleObj;
    }

    public ContractorObj getContractorObj() {
        return contractorObj;
    }

    public void setContractorObj(ContractorObj contractorObj) {
        this.contractorObj = contractorObj;
    }

    public CityObj getCityObj() {
        return cityObj;
    }

    public void setCityObj(CityObj cityObj) {
        this.cityObj = cityObj;
    }
}
