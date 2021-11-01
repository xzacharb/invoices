package com.xzacharb.coresvc.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "legal_forms")
public final class LegalForm {
    @Id
    @Column(length = 128)
    private String short_cut;
    @Column(length = 255, unique = true, nullable = false)
    private String name;

    public LegalForm() {
    }

    public LegalForm(String shortCut, String name) {
        this.short_cut = shortCut;
        this.name = name;
    }

    public String getShort_cut() {
        return short_cut;
    }

    public void setShort_cut(String short_cut) {
        this.short_cut = short_cut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
