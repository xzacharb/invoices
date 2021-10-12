package com.xzacharb.coresvc.model.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contractors")
public class ContractorDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 255)
    private String name;
    @Column(length = 255)
    private String address;
    @Column(length = 512)
    private String description;
    @Column(length = 255)
    private String source;
    @Column(length = 64)
    private String ico;
    @Temporal(TemporalType.DATE)
    private Date date_created;
    @ManyToOne
    @JoinColumn(name = "legal_form_id", referencedColumnName = "short_cut")
    private LegalFormDao legalFormId;

    public ContractorDao() {
    }

    public ContractorDao(String name, String address, String description, String source, String ico, Date dateCreated, LegalFormDao legalFormDao) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.source = source;
        this.ico = ico;
        this.date_created = dateCreated;
        this.legalFormId = legalFormDao;
    }

    public ContractorDao(long id, String name, String address, String description, String source, String ico, Date dateCreated, LegalFormDao legalFormDao) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.source = source;
        this.ico = ico;
        this.date_created = dateCreated;
        this.legalFormId = legalFormDao;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public LegalFormDao getLegalFormId() {
        return legalFormId;
    }

    public void setLegalFormId(LegalFormDao legalFormId) {
        this.legalFormId = legalFormId;
    }
}
