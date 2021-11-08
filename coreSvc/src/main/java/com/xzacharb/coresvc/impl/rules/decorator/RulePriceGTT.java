package com.xzacharb.coresvc.impl.rules.decorator;

import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.infra.rules.EvaluableRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RulePriceGTT extends RuleDecorator {
    private static int BASE_SCORE = 2;
    public RulePriceGTT(EvaluableRule ruleDecorator, List<Integer> thresholds) {
        super(ruleDecorator, thresholds);
    }

    @Override
    public List<InvoiceDao> execute(List<InvoiceDao> invoices) {

        if (!thresholds.isEmpty()
                && thresholds.size() >= 1
                && thresholds.get(0) instanceof Integer
        ) {
            List<InvoiceDao> filtered = invoices.stream()
                    .filter(
                            oneInvoice -> oneInvoice.getPrice() > super.thresholds.get(0)
                    ).collect(Collectors.toList());
            if (filtered.size() > 0) {
                super.score += filtered.size() * BASE_SCORE;
                super.description = "RulePriceGTT ";
                return this.ruleDecorator.execute(filtered);
            }
        }
        return new ArrayList<>();
    }
}
