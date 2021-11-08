package com.xzacharb.coresvc.infra.rules;

import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;

import java.util.List;

public interface Rule {
    public List<InvoiceDao> execute(List<InvoiceDao> invoices);
    public String description();
    public int score();
}
