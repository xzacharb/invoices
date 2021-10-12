package com.xzacharb.coresvc.model.objects;

import java.util.Date;

public class ContractorObj {
    private long id;
    private String name;
    private String address;
    private String description;
    private String source;
    private String ico;
    private Date dateCreated;
    private LegalFormObj legaFormObj;

    public ContractorObj() {
    }

    public ContractorObj(String name, String address, String description, String source, String ico, Date dateCreated, LegalFormObj legaFormObj) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.source = source;
        this.ico = ico;
        this.dateCreated = dateCreated;
        this.legaFormObj = legaFormObj;
    }

    public ContractorObj(long id, String name, String address, String description, String source, String ico, Date dateCreated, LegalFormObj legaFormObj) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.source = source;
        this.ico = ico;
        this.dateCreated = dateCreated;
        this.legaFormObj = legaFormObj;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LegalFormObj getLegaFormObj() {
        return legaFormObj;
    }

    public void setLegaFormObj(LegalFormObj legaFormObj) {
        this.legaFormObj = legaFormObj;
    }
}
