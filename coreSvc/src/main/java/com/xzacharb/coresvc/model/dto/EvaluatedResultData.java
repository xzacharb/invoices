package com.xzacharb.coresvc.model.dto;

import com.xzacharb.coresvc.model.dao.EvaluatedResult;

public class EvaluatedResultData {
    private String value;
    private String description;
    private String evaluatorName;
    private long invoiceId;

    public EvaluatedResultData() {
    }

    public EvaluatedResultData(long invoiceId, String value, String description, String evaluator_name) {
        this.invoiceId = invoiceId;
        this.value = value;
        this.description = description;
        this.evaluatorName = evaluator_name;
    }
    public EvaluatedResultData(EvaluatedResult evaluatedResult) {
        this.invoiceId = evaluatedResult.getInvoiceDao().getId();
        this.value = evaluatedResult.getValue();
        this.description = evaluatedResult.getDescription();
        this.evaluatorName = evaluatedResult.getEvaluator_name();
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getEvaluatorName() {
        return evaluatorName;
    }

    public long getInvoiceId() {
        return invoiceId;
    }
}
