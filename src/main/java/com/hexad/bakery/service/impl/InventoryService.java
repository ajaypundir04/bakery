package com.hexad.bakery.service.impl;

import com.hexad.bakery.models.Inventory;
import com.hexad.bakery.models.Product;

import java.util.Map;

/**
 * @author Ajay Singh Pundir
 * This class used for mainting the products with their availability.
 */
public class InventoryService {


    private Inventory inventory;

    public InventoryService() {
        inventory = new Inventory();
    }

    /**
     * @param code On the basis of code it will return available quantity
     * @return available quantity in @int
     */
    public int getAvailableQuantities(String code) {
        for (Map.Entry<Product, Integer> entry : inventory.getInventoryMap().entrySet()) {
            if (entry.getKey().getCode().equalsIgnoreCase(code))
                return entry.getValue();
        }
        return 0;
    }

    /**
     * @param code On the basis of product it will return it's details
     * @return @{@link Product} object is returned from the @productCodeAvailabilityMap
     */
    public Product getProductByCode(String code) {
        for (Map.Entry<Product, Integer> entry : inventory.getInventoryMap().entrySet()) {
            if (entry.getKey().getCode().equalsIgnoreCase(code))
                return entry.getKey();
        }
        return null;
    }

    /**
     * @param product       @{@link Product} is passed for modifying the inventory.
     * @param quantToModify quantity of products to be modified.It can be +ve for add and -ve for delete
     */
    public void addOrUpdateInventory(Product product, int quantToModify) {// inventory =new Inventory();
        if (inventory.getInventoryMap().containsKey(product)) {
            inventory.getInventoryMap().put(product, inventory.getInventoryMap().get(product) + quantToModify);
        } else {
            inventory.getInventoryMap().put(product, quantToModify);
        }

    }

    /**
     * @param product is passed to remove it from inventory
     * @return no of items removed
     */
    public int removeProductFromInventory(Product product) {
        return inventory.getInventoryMap().remove(product);
    }

}
