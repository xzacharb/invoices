package com.xzacharb.coresvc.impl.model.dto;

public class InvoicePerson {
    String city;

    public InvoicePerson() {
    }

    public InvoicePerson(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    /*InvoiceDao invoiceDao;
    List<ManagementPerson> personList;

    public InvoicePerson() {
    }

    public InvoicePerson(InvoiceDao invoiceDao, List<ManagementPerson> personList) {
        this.invoiceDao = invoiceDao;
        this.personList = personList;
    }

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public List<ManagementPerson> getPersonList() {
        return personList;
    }

    public void setPersonList(List<ManagementPerson> personList) {
        this.personList = personList;
    }*/
}
