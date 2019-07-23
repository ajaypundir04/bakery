package com.hexad.bakery.model;

import java.util.List;

public class Product {

    private String name;
    private String code;
    private List<Packs> packs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Packs> getPacks() {
        return packs;
    }

    public void setPacks(List<Packs> packs) {
        this.packs = packs;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", packs=" + packs +
                '}';
    }
}
