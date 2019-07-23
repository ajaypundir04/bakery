package com.hexad.bakery.models;

import java.util.Objects;


public class Pack implements Comparable<Pack> {

    private int quantity;
    private float price;

    public Pack() {
    }

    public Pack(int quantity, float price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Pack setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Pack setPrice(float price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pack pack = (Pack) o;
        return quantity == pack.quantity &&
                Float.compare(pack.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price);
    }

    @Override
    public int compareTo(Pack o) {
        return Integer.compare(this.quantity, o.quantity);
    }

    @Override
    public String toString() {
        return quantity + " $" + price;
    }
}
