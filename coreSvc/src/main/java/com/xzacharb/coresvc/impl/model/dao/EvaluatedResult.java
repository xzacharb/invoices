package com.xzacharb.coresvc.impl.model.dao;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "evaluation_results")
public final class EvaluatedResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 512)
    private String value;
    @Column(length = 512)
    private String description;
    @Column(length = 128)
    private String evaluatorNameShort;
    @Column(length = 128)
    private String evaluator_name;
    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private InvoiceDao invoiceDao;
    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractorId;

    public EvaluatedResult() {
    }

    public EvaluatedResult(long id, String value, String description, String evaluator_name, InvoiceDao invoiceDao) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.evaluator_name = evaluator_name;
        this.invoiceDao = invoiceDao;
        this.contractorId = invoiceDao.getContractor();
    }

    public EvaluatedResult(String value, String description, String evaluator_name, InvoiceDao invoiceDao) {
        this.value = value;
        this.description = description;
        this.evaluator_name = evaluator_name;
        this.evaluatorNameShort = evaluator_name.toLowerCase(Locale.ROOT).replaceAll("\\s+","");
        this.invoiceDao = invoiceDao;
        this.contractorId = invoiceDao.getContractor();
    }

    public long getId() {
        return id;
    }

    public String getEvaluatorNameShort() {
        return evaluatorNameShort;
    }

    public void setEvaluatorNameShort(String evaluatorNameShort) {
        this.evaluatorNameShort = evaluatorNameShort;
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

    public Contractor getContractorId() {
        return contractorId;
    }

    public void setContractorId(Contractor contractorId) {
        this.contractorId = contractorId;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }
}
