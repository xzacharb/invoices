package com.xzacharb.coresvc.model.dto;

import com.xzacharb.coresvc.model.dao.City;
import com.xzacharb.coresvc.model.dao.Contractor;
import com.xzacharb.coresvc.model.dao.InvoiceDao;

import java.util.Date;
import java.util.List;

public class InvoiceDataKafka {
    private long id;
    private int price;
    private String subject;
    private String description;
    private String comment;
    private Date date_signed;
    private Date date_published;
    private String source;
    private City city;
    private ContractorKafka contractor;
    private List<ManagementPerson> personList;


    public InvoiceDataKafka() {
    }

    public ContractorKafka getContractor() {
        return contractor;
    }

    public List<ManagementPerson> getPersonList() {
        return personList;
    }


    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate_signed() {
        return date_signed;
    }

    public Date getDate_published() {
        return date_published;
    }

    public String getSource() {
        return source;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "InvoiceData{" +
                "price=" + price +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", date_signed=" + date_signed +
                ", date_published=" + date_published +
                ", source='" + source + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
