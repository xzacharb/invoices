package com.xzacharb.coresvc.impl.rules.decorator;

import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.impl.model.dto.InvoiceData;
import com.xzacharb.coresvc.infra.rules.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleSumBetween extends RuleDecorator {
    private static int BASE_SCORE = 4;
    public RuleSumBetween(Rule ruleDecorator, List<Integer> thresholds) {
        super(ruleDecorator, thresholds);
    }

    @Override
    public List<InvoiceDao> execute(List<InvoiceDao> invoices) {
        if (!invoices.isEmpty()
                && !thresholds.isEmpty()
                && thresholds.size() >= 2
                && thresholds.get(0) instanceof Integer
                && thresholds.get(1) instanceof Integer
        ) {
            List<InvoiceDao> filtered = invoices.stream()
                    .filter(
                            invoice ->
                                    invoice.getPrice() >= (Integer) thresholds.get(0)
                                            && invoice.getPrice() <= (Integer) thresholds.get(1)
                    ).collect(Collectors.toList());
            if (filtered.size() > 0) {
                super.score += filtered.size() * BASE_SCORE;
                super.description = "RuleSumBetween ";
                return this.ruleDecorator.execute(filtered);
            }
        }
        return new ArrayList<>();
    }
}
