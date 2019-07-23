package com.hexad.bakery.models;


public class Order {

    private int quantity;
    private String code;

    public int getQuantity() {
        return quantity;
    }

    public Order setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Order setCode(String code) {
        this.code = code;
        return this;
    }
}
