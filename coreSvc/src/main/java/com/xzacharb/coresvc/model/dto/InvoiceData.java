package com.xzacharb.coresvc.model.dto;

import com.xzacharb.coresvc.model.dao.City;
import com.xzacharb.coresvc.model.dao.Contractor;
import com.xzacharb.coresvc.model.dao.InvoiceDao;

import java.util.Date;

public class InvoiceData {
    private long id;
    private int price;
    private String subject;
    private String description;
    private String comment;
    private Date date_signed;
    private Date date_published;
    private String source;
    private String city;
    private long contractorId;
    private String contractorName;

    public InvoiceData() {
    }

    public InvoiceData(InvoiceDao invoiceObj) {
        this.id = invoiceObj.getId();
        this.price = invoiceObj.getPrice();
        this.subject = invoiceObj.getSubject();
        this.description = invoiceObj.getDescription();
        this.comment = invoiceObj.getComment();
        this.date_signed = invoiceObj.getDate_signed();
        this.date_published = invoiceObj.getDate_published();
        this.source = invoiceObj.getSource();
        this.city = invoiceObj.getCity().getCity_name();
        this.contractorId = invoiceObj.getContractor().getId();
        this.contractorName = invoiceObj.getContractor().getName();
    }

    public InvoiceData(long id,int price, String subject, String description, String comment, Date date_signed, Date date_published, String source, City city) {
        this.id = id;
        this.price = price;
        this.subject = subject;
        this.description = description;
        this.comment = comment;
        this.date_signed = date_signed;
        this.date_published = date_published;
        this.source = source;
        this.city = city.getCity_name();
    }
    public InvoiceDao createDao(City city,Contractor contractor){
        return new InvoiceDao(this.price, this.subject, this.description, this.comment, this.date_signed, this.date_published, this.source, city, contractor);
    }

    public long getContractorId() {
        return contractorId;
    }

    public String getContractorName() {
        return contractorName;
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

    public String getCity() {
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
