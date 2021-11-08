package com.xzacharb.coresvc.impl.rules.decorator;

import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.infra.rules.EvaluableRule;

import java.util.List;


public class BaseRule implements EvaluableRule {

    @Override
    public List<InvoiceDao> execute(List<InvoiceDao> invoices) {
        return invoices;
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public int score() {
        return 0;
    }

}
