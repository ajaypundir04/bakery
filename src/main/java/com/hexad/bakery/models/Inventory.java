package com.hexad.bakery.models;


public class Inventory {

    private Product product;

    private int availableQuantities;

    public Inventory() {
    }

    public Inventory(Product product, int availableQuantities) {
        this.product = product;
        this.availableQuantities = availableQuantities;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAvailableQuantities() {
        return availableQuantities;
    }

    public void setAvailableQuantities(int availableQuantities) {
        this.availableQuantities = availableQuantities;
    }
}
