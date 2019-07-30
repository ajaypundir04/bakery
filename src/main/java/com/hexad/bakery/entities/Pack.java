package com.hexad.bakery.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "PACKS")
public class Pack extends AbstractEntity implements Comparable<Pack> {

    private int quantity;
    private float price;

    private Product product;

    public Pack() {
    }

    public Pack(int quantity, float price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
