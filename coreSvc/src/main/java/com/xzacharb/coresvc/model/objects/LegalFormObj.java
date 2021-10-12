package com.xzacharb.coresvc.model.objects;

public class LegalFormObj {
    private String shortCut;
    private String name;

    public LegalFormObj() {
    }

    public LegalFormObj(String shortCut, String name) {
        this.shortCut = shortCut;
        this.name = name;
    }

    public String getShortCut() {
        return shortCut;
    }

    public void setShortCut(String shortCut) {
        this.shortCut = shortCut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
