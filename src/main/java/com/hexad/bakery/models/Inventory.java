package com.hexad.bakery.models;


import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Product, Integer> inventoryMap;

    public Inventory() {
        inventoryMap = new HashMap<>();
    }

    public Map<Product, Integer> getInventoryMap() {
        return inventoryMap;
    }

    public void setInventoryMap(Map<Product, Integer> inventoryMap) {
        this.inventoryMap = inventoryMap;
    }

}
