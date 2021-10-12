package com.xzacharb.coresvc.model.objects;

import com.xzacharb.coresvc.model.dao.InvoiceDao;

public class EvaluatedResultObj {
    private long id;
    private String value;
    private String description;
    private String evaluator_name;
    private InvoiceDao invoiceDao;

    public EvaluatedResultObj() {
    }

    public EvaluatedResultObj(long id, String value, String description, String evaluator_name, InvoiceDao invoiceDao) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.evaluator_name = evaluator_name;
        this.invoiceDao = invoiceDao;
    }

    public EvaluatedResultObj(String value, String description, String evaluator_name, InvoiceDao invoiceDao) {
        this.value = value;
        this.description = description;
        this.evaluator_name = evaluator_name;
        this.invoiceDao = invoiceDao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvaluator_name() {
        return evaluator_name;
    }

    public void setEvaluator_name(String evaluator_name) {
        this.evaluator_name = evaluator_name;
    }

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }
}
