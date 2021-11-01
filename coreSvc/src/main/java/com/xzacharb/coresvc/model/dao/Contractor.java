package com.xzacharb.coresvc.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contractors")
public final class Contractor {
    @JsonIgnore
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "legal_form_id", referencedColumnName = "short_cut")
    private LegalForm legalFormId;

    public Contractor() {
    }

    public Contractor(String name, String address, String description, String source, String ico, Date dateCreated) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.source = source;
        this.ico = ico;
        this.date_created = dateCreated;
    }

    public Contractor(String name, String address, String description, String source, String ico, Date dateCreated, LegalForm legalFormDao) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.source = source;
        this.ico = ico;
        this.date_created = dateCreated;
        this.legalFormId = legalFormDao;
    }

    public Contractor(long id, String name, String address, String description, String source, String ico, Date dateCreated, LegalForm legalFormDao) {
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

    public LegalForm getLegalFormId() {
        return legalFormId;
    }

    public void setLegalFormId(LegalForm legalFormId) {
        this.legalFormId = legalFormId;
    }
}
