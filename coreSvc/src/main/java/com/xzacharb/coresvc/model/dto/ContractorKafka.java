package com.xzacharb.coresvc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xzacharb.coresvc.model.dao.Contractor;
import com.xzacharb.coresvc.model.dao.LegalForm;

import javax.persistence.*;
import java.util.Date;

public final class ContractorKafka {

    private String name;
    private String address;
    private String description;
    private String source;
    private String ico;
    private Date date_created;
    private String legalFormId;

    public ContractorKafka() {
    }

    public void setLegalFormId(String legalFormId) {
        this.legalFormId = legalFormId;
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

    public String getLegalFormId() {
        return legalFormId;
    }
}
