package com.xzacharb.coresvc.model.dao;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "invoices")
public class InvoiceDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int price;
    @Column(length = 512)
    private String subject;
    @Column(length = 512)
    private String description;
    @Column(length = 512)
    private String comment;
    @Temporal(TemporalType.DATE)
    private Date date_signed;
    @Temporal(TemporalType.DATE)
    private Date date_published;
    @Column
    private String source;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_name")
    private CityDao cityDao;
    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private ContractorDao contractorDao;

    public InvoiceDao() {
    }

    public InvoiceDao(long id, int price, String subject, String description, String comment, Date date_signed, Date date_published, String source, CityDao cityDao, ContractorDao contractorDao) {
        this.id = id;
        this.price = price;
        this.subject = subject;
        this.description = description;
        this.comment = comment;
        this.date_signed = date_signed;
        this.date_published = date_published;
        this.source = source;
        this.cityDao = cityDao;
        this.contractorDao = contractorDao;
    }

    public InvoiceDao(int price, String subject, String description, String comment, Date date_signed, Date date_published, String source, CityDao cityDao, ContractorDao contractorDao) {
        this.price = price;
        this.subject = subject;
        this.description = description;
        this.comment = comment;
        this.date_signed = date_signed;
        this.date_published = date_published;
        this.source = source;
        this.cityDao = cityDao;
        this.contractorDao = contractorDao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate_signed() {
        return date_signed;
    }

    public void setDate_signed(Date date_signed) {
        this.date_signed = date_signed;
    }

    public Date getDate_published() {
        return date_published;
    }

    public void setDate_published(Date date_published) {
        this.date_published = date_published;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public ContractorDao getContractorDao() {
        return contractorDao;
    }

    public void setContractorDao(ContractorDao contractorDao) {
        this.contractorDao = contractorDao;
    }
}
