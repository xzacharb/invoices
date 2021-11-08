package com.xzacharb.coresvc.infra.rules;

import com.xzacharb.coresvc.impl.model.dto.InvoiceData;

import java.util.List;

public interface RuleFactory {
    public Rule createRule(String name, List<Integer> thresholds);
}
