package com.xzacharb.coresvc.impl.model.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "management_persons")
public final class ManagementPersonDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 128)
    private String name;
    @Column(length = 255)
    private String middle_name;
    @Column(length = 128)
    private String sure_name;
    @Column(length = 512)
    private String address;
    @Column(length = 255)
    private String source;
    @Temporal(TemporalType.DATE)
    private Date date_start;
    @ManyToOne
    @JoinColumn(name = "management_type_id", referencedColumnName = "type_name")
    private ManagementType managementType;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_name")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractorObjDao;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_short")
    private City cityObjDao;

    public ManagementPersonDao() {
    }

    public ManagementPersonDao(long id, String name, String middleName, String sureName, String address, String source, Date dateStart, Role role, Contractor contractorObjDao, City cityObjDao, ManagementType managementType) {
        this.id = id;
        this.name = name;
        this.middle_name = middleName;
        this.sure_name = sureName;
        this.address = address;
        this.source = source;
        this.date_start = dateStart;
        this.role = role;
        this.contractorObjDao = contractorObjDao;
        this.cityObjDao = cityObjDao;
        this.managementType = managementType;
    }

    public ManagementPersonDao(String name, String middleName, String sureName, String address, String source, Date dateStart, Role role, Contractor contractorObjDao, City cityObjDao, ManagementType managementType) {
        this.name = name;
        this.middle_name = middleName;
        this.sure_name = sureName;
        this.address = address;
        this.source = source;
        this.date_start = dateStart;
        this.role = role;
        this.contractorObjDao = contractorObjDao;
        this.cityObjDao = cityObjDao;
        this.managementType = managementType;
    }

    public ManagementType getManagementTypeDao() {
        return managementType;
    }

    public void setManagementTypeDao(ManagementType managementType) {
        this.managementType = managementType;
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

    public String getSure_name() {
        return sure_name;
    }

    public void setSure_name(String sure_name) {
        this.sure_name = sure_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
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

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Role getRoleDao() {
        return role;
    }

    public void setRoleDao(Role role) {
        this.role = role;
    }

    public Contractor getContractorObjDao() {
        return contractorObjDao;
    }

    public void setContractorObjDao(Contractor contractorObjDao) {
        this.contractorObjDao = contractorObjDao;
    }

    public City getCityObjDao() {
        return cityObjDao;
    }

    public void setCityObjDao(City cityObjDao) {
        this.cityObjDao = cityObjDao;
    }
}
