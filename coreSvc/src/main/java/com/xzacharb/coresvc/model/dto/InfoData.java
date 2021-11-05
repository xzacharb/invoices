package com.xzacharb.coresvc.model.dto;

import com.xzacharb.coresvc.model.dao.City;

public class InfoData {
    private final String name;
    private final String identifier;
    private final long count;

    public InfoData(String name, String identifier, long count) {
        this.name = name;
        this.identifier = identifier;
        this.count = count;
    }
    public InfoData(String name, long identifier, long count) {
        this.name = name;
        this.identifier = String.valueOf(identifier);
        this.count = count;
    }
    public InfoData(City name, long count) {
        this.name = name.getCity_name();
        this.identifier = name.getCity_short();
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public String getIdentifier() {
        return identifier;
    }
}
