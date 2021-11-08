package com.xzacharb.coresvc.infra.rules;

import com.xzacharb.coresvc.impl.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;

import java.util.List;

public interface Rules {
    /**
     * Rule: Sum of filtered Invoices higher than threshold.
     * @param invoices invoices
     * @return List<EvaluatedResult>
     */
    public List<EvaluatedResult> filteredSumOverThreshold(List<InvoiceDao> invoices);

    /**
     * Rule: total sum of invoices higher than threshold
     *
     * @param invoices invoices
     * @return List<EvaluatedResult>
     */
    public List<EvaluatedResult> totalSumOverThreshold(List<InvoiceDao> invoices);
}
