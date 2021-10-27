package com.xzacharb.coresvc.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "evaluation_results")
public class EvaluatedResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 512)
    private String value;
    @Column(length = 512)
    private String description;
    @Column(length = 128)
    private String evaluator_name_short;
    @Column(length = 128)
    private String evaluator_name;
    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private InvoiceDao invoiceDao;

    public EvaluatedResult() {
    }

    public EvaluatedResult(long id, String value, String description, String evaluator_name, InvoiceDao invoiceDao) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.evaluator_name = evaluator_name;
        this.invoiceDao = invoiceDao;
    }

    public EvaluatedResult(String value, String description, String evaluator_name, InvoiceDao invoiceDao) {
        this.value = value;
        this.description = description;
        this.evaluator_name = evaluator_name;
        this.evaluator_name_short = evaluator_name.replaceAll("\\s+","");
        this.invoiceDao = invoiceDao;
    }

    public long getId() {
        return id;
    }

    public String getEvaluator_name_short() {
        return evaluator_name_short;
    }

    public void setEvaluator_name_short(String evaluator_name_short) {
        this.evaluator_name_short = evaluator_name_short;
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
