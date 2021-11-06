package com.xzacharb.coresvc.impl.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public final class City {
    @Id
    @Column(length = 10)
    private String city_short;
    @Column(length = 255)
    private String city_name;

    public City() {
    }

    public City(String city_short, String city_name) {
        this.city_short = city_short;
        this.city_name = city_name;
    }

    public final String getCity_short() {
        return city_short;
    }

    public void setCity_short(String city_short) {
        this.city_short = city_short;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
