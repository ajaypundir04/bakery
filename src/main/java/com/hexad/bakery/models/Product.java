package com.hexad.bakery.models;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Product {

    private String name;
    private String code;

    private Set<Pack> packs;

    public Product() {
    }

    public Product(String name, String code, Set<Pack> packs) {
        this.name = name;
        this.code = code;
        this.setPacks(packs);
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Product setCode(String code) {
        this.code = code;
        return this;
    }

    public Set<Pack> getPacks() {
        return packs;
    }

    public Product setPacks(Set<Pack> packs) {
        this.packs = new TreeSet<>(packs);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
