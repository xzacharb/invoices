package com.xzacharb.coresvc.common;

import com.xzacharb.coresvc.model.dao.City;

public class CityCount {
    private final String city;
    private final long count;

    public CityCount(String city, long count) {
        this.city = city;
        this.count = count;
    }
    public CityCount(City city, long count) {
        this.city = city.getCity_name();
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public long getCount() {
        return count;
    }
}
