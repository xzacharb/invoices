package com.xzacharb.coresvc.impl.rules.decorator;

import com.xzacharb.coresvc.infra.rules.EvaluableRule;

import java.util.List;

public abstract class RuleDecorator implements EvaluableRule {
    String description;
    int score;
    EvaluableRule ruleDecorator;
    List<Integer> thresholds;

    public RuleDecorator(EvaluableRule ruleDecorator, List<Integer> threshold) {
        this.ruleDecorator = ruleDecorator;
        this.thresholds = threshold;
        this.description = "";
        this.score = 0;
    }

    @Override
    public int score() {
        return score + this.ruleDecorator.score();
    }

    @Override
    public String description() {
        StringBuffer sb = new StringBuffer(15);
        thresholds.forEach(t -> sb.append(t + " "));
        String desc = " name:" + description +" thresholds:" + sb + " score: " + score +"; "+  this.ruleDecorator.description();
        return description.isEmpty() ? "" : desc;
    }

}
