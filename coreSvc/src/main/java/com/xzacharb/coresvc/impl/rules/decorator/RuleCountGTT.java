package com.xzacharb.coresvc.impl.rules.decorator;

import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.infra.rules.EvaluableRule;

import java.util.ArrayList;
import java.util.List;

public class RuleCountGTT extends RuleDecorator {
    private static int BASE_SCORE = 3;

    public RuleCountGTT(EvaluableRule ruleDecorator, List<Integer> thresholds) {
        super(ruleDecorator, thresholds);
    }

    @Override
    public List<InvoiceDao> execute(List<InvoiceDao> invoices) {
        if (!super.thresholds.isEmpty()
                && super.thresholds.get(0) instanceof Integer
                && invoices.size() > super.thresholds.get(0)
        ) {
            super.description = "RuleCountGTT ";
            super.score += BASE_SCORE * invoices.size();
            return this.ruleDecorator.execute(invoices);
        }
        return new ArrayList<>();

    }

}
