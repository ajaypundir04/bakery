package com.hexad.bakery.model;

import java.util.List;
import java.util.Map;

public class Inventory {

    private Map<String,Product> products;

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                '}';
    }
}
