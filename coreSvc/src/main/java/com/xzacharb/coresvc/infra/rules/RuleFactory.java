package com.xzacharb.coresvc.infra.rules;

import java.util.List;

public interface RuleFactory {
    public EvaluableRule createRule(String name, List<Integer> thresholds);
}
