package com.xzacharb.coresvc.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @Column(length = 255)
    private String city_name;

    public City() {
    }

    public City(String cityName) {
        this.city_name = cityName;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
