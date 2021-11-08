package com.xzacharb.coresvc.impl.rules.decorator;

import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.infra.rules.EvaluableRule;

import java.util.ArrayList;
import java.util.List;

public class RuleSumGTT extends RuleDecorator {
    private static int BASE_SCORE = 10;
    public RuleSumGTT(EvaluableRule ruleDecorator, List<Integer> thresholds) {
        super(ruleDecorator, thresholds);
    }

    @Override
    public List<InvoiceDao> execute(List<InvoiceDao> invoices) {
        int sum = 0;
        sum += invoices.stream().mapToInt(InvoiceDao::getPrice).sum();
        if (!thresholds.isEmpty()
                && thresholds.size() >= 1
                && thresholds.get(0) instanceof Integer
                && sum > thresholds.get(0)) {
            super.score += BASE_SCORE * (sum/thresholds.get(0));
            super.description = "RuleSumGTT ";
            return this.ruleDecorator.execute(invoices);
        }
        return new ArrayList<>();
    }
}
