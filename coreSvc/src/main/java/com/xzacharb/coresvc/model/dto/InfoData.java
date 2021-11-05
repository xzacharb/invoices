package com.xzacharb.coresvc.model.dto;

import com.xzacharb.coresvc.model.dao.City;

public class CityCount {
    private final String city;
    private final String cityShort;
    private final long count;

    public CityCount(String city,String cityShort, long count) {
        this.city = city;
        this.cityShort = cityShort;
        this.count = count;
    }
    public CityCount(City city, long count) {
        this.city = city.getCity_name();
        this.cityShort = city.getCity_short();
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public long getCount() {
        return count;
    }

    public String getCityShort() {
        return cityShort;
    }
}
