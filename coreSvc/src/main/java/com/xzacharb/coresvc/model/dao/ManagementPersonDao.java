package com.xzacharb.coresvc.model.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "management_persons")
public class ManagementPersonDao {
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
    private ManagementTypeDao managementTypeDao;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_name")
    private RoleDao roleDao;
    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private ContractorDao contractorObjDao;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_name")
    private CityDao cityObjDao;

    public ManagementPersonDao() {
    }

    public ManagementPersonDao(long id, String name, String middleName, String sureName, String address, String source, Date dateStart, RoleDao roleDao, ContractorDao contractorObjDao, CityDao cityObjDao, ManagementTypeDao managementTypeDao) {
        this.id = id;
        this.name = name;
        this.middle_name = middleName;
        this.sure_name = sureName;
        this.address = address;
        this.source = source;
        this.date_start = dateStart;
        this.roleDao = roleDao;
        this.contractorObjDao = contractorObjDao;
        this.cityObjDao = cityObjDao;
        this.managementTypeDao = managementTypeDao;
    }

    public ManagementPersonDao(String name, String middleName, String sureName, String address, String source, Date dateStart, RoleDao roleDao, ContractorDao contractorObjDao, CityDao cityObjDao, ManagementTypeDao managementTypeDao) {
        this.name = name;
        this.middle_name = middleName;
        this.sure_name = sureName;
        this.address = address;
        this.source = source;
        this.date_start = dateStart;
        this.roleDao = roleDao;
        this.contractorObjDao = contractorObjDao;
        this.cityObjDao = cityObjDao;
        this.managementTypeDao = managementTypeDao;
    }

    public ManagementTypeDao getManagementTypeDao() {
        return managementTypeDao;
    }

    public void setManagementTypeDao(ManagementTypeDao managementTypeDao) {
        this.managementTypeDao = managementTypeDao;
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

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public ContractorDao getContractorObjDao() {
        return contractorObjDao;
    }

    public void setContractorObjDao(ContractorDao contractorObjDao) {
        this.contractorObjDao = contractorObjDao;
    }

    public CityDao getCityObjDao() {
        return cityObjDao;
    }

    public void setCityObjDao(CityDao cityObjDao) {
        this.cityObjDao = cityObjDao;
    }
}
