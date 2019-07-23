package com.hexad.bakery.service;

import com.hexad.bakery.models.Inventory;
import com.hexad.bakery.models.Product;

import java.util.HashMap;
import java.util.Map;


public class InventoryService {

    private Map<String, Inventory> inventoryMap;
    private final Product EMPTY_PRODUCT = new Product();

    public InventoryService() {
        inventoryMap = new HashMap<>();
    }

    public int getAvailableQuantities(String code) {
        return inventoryMap.getOrDefault(code, new Inventory(EMPTY_PRODUCT, 0)).getAvailableQuantities();
    }

    public Product getProductByCode(String code) {
        if (inventoryMap.containsKey(code)) {
            return inventoryMap.get(code).getProduct();
        }
        return null;
    }

    public void addOrUpdateInventory(Product product, int newQuantitiesToAdd) {
        Inventory inventory = inventoryMap.getOrDefault(product.getCode(), new Inventory(EMPTY_PRODUCT, 0));
        inventory.setProduct(product);
        inventory.setAvailableQuantities(inventory.getAvailableQuantities() + newQuantitiesToAdd);
        inventoryMap.put(product.getCode(), inventory);
    }
}
