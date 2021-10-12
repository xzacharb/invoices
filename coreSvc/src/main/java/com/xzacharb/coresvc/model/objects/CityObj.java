package com.xzacharb.coresvc.model.objects;

public class CityObj {
    private String cityName;

    public CityObj() {
    }

    public CityObj(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
