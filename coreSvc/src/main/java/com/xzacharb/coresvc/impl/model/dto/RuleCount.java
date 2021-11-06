package com.xzacharb.coresvc.impl.model.dto;

public class RuleCount {
    private final String ruleName;
    private final String ruleNameShort;
    private final String city;
    private final String cityShort;
    private final String ruleDescription;
    private final long count;

    public RuleCount(String ruleName, String city, String cityShort, String ruleDescription, String ruleNameShort, long count) {
        this.ruleName = ruleName;
        this.city = city;
        this.cityShort = cityShort;
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

    public String getCityShort() {
        return cityShort;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public long getCount() {
        return count;
    }
}
