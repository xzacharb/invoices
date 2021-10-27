package com.xzacharb.coresvc.common;

public class RuleCount {
    private final String ruleName;
    private final String ruleNameShort;
    private final String city;
    private final String ruleDescription;
    private final long count;

    public RuleCount(String ruleName, String city, String ruleDescription, String ruleNameShort, long count) {
        this.ruleName = ruleName;
        this.city = city;
        this.ruleDescription = ruleDescription;
        this.ruleNameShort = ruleNameShort;
        this.count = count;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getRuleNameShort() {
        return ruleNameShort;
    }

    public String getCity() {
        return city;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public long getCount() {
        return count;
    }
}
