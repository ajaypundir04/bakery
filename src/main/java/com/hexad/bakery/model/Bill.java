package com.hexad.bakery.model;

import java.util.Map;

public class Bill {

    private ItemOrder itemOrder;
    private double billAmount;
    private Map<Packs,Integer> items;

    public ItemOrder getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(ItemOrder itemOrder) {
        this.itemOrder = itemOrder;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public Map<Packs, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Packs, Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "itemOrder=" + itemOrder +
                ", billAmount=" + billAmount +
                ", items=" + items +
                '}';
    }
}
