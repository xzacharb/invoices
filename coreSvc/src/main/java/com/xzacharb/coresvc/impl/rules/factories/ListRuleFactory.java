package com.xzacharb.coresvc.impl.rules.factories;

import com.xzacharb.coresvc.impl.model.dto.InvoiceData;
import com.xzacharb.coresvc.impl.rules.decorator.*;
import com.xzacharb.coresvc.infra.rules.Rule;
import com.xzacharb.coresvc.infra.rules.RuleFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ListRuleFactory implements RuleFactory {
    @Override
    public Rule createRule(String name, List<Integer> thresholds) {
        int size= thresholds.size();
        switch (name){
            case "PriceBetweenThresholdsSumOverThreshold":
                return new RuleSumBetween(new RuleSumGTT(new RuleCountGTT(new BaseRule(),thresholds.subList(size-1,size)),thresholds.subList(2,3)),thresholds.subList(0,2));
            case "PriceOverThresholdSumOverThresholdCountOverThreshold":
                return new RulePriceGTT(new RuleSumGTT(new RuleCountGTT(new BaseRule(),thresholds.subList(2,3)),thresholds.subList(1,2)),thresholds.subList(0,1));
            default: return new BaseRule();
        }
    }
}
