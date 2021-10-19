package com.xzacharb.coresvc.model.data;

import com.xzacharb.coresvc.model.dao.InvoiceDao;

import javax.persistence.*;

public class EvaluatedResultData {
    private String value;
    private String description;
    private String evaluator_name;
    private String invoiceId;

    public EvaluatedResultData() {
    }

    public EvaluatedResultData(String invoiceId, String value, String description, String evaluator_name) {
        this.invoiceId = invoiceId;
        this.value = value;
        this.description = description;
        this.evaluator_name = evaluator_name;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getEvaluator_name() {
        return evaluator_name;
    }

    public String getInvoiceId() {
        return invoiceId;
    }
}
