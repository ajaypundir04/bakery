package com.hexad.bakery.model;

/**
 *  It is used to store the package details of product
 */
public class Packs implements Comparable<Packs>{

    private int quantity;
    private double price;
    private int availablePacks;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailablePacks() {
        return availablePacks;
    }

    public void setAvailablePacks(int availablePacks) {
        this.availablePacks = availablePacks;
    }

    @Override
    public String toString() {
        return "Packs{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", availablePacks=" + availablePacks +
                '}';
    }

    @Override
    public int compareTo(Packs o) {

        if(this.quantity>o.quantity)
            return 1;
        else if(this.quantity<o.quantity)
            return -1;
        else
            return 0;
    }
}
