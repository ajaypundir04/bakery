package com.hexad.bakery.service.impl;

import com.hexad.bakery.entities.Inventory;
import com.hexad.bakery.entities.Product;
import com.hexad.bakery.repositories.InventoryRepository;
import com.hexad.bakery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ajay Singh Pundir
 * This class used for mainting the products with their availability.
 */
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    /**
     * @param code On the basis of code it will return available quantity
     * @return available quantity in @int
     */
    public int getAvailableQuantities(String code) {
        return inventoryRepository.findByProduct_code(code).getQuantity();
    }

    /**
     * @param code On the basis of product it will return it's details
     * @return @{@link Product} object is returned from the @productCodeAvailabilityMap
     */
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    /**
     * @param product       @{@link Product} is passed for modifying the inventory.
     * @param quantToModify quantity of products to be modified.It can be +ve for add and -ve for delete
     */
    public void addOrUpdateInventory(Product product, int quantToModify) {// inventory =new Inventory();
        Inventory inventory = inventoryRepository.findByProduct_code(product.getCode());
        if(inventory == null) {
            inventory = new Inventory();
            inventory.setQuantity(0);
        }
        inventory.setProduct(product);
        inventory.setQuantity(inventory.getQuantity() + quantToModify);
        inventoryRepository.save(inventory);
    }

    /**
     * @param product is passed to remove it from inventory
     */
    public void removeProductFromInventory(Product product) {
        inventoryRepository.deleteByProduct(product);
    }
}
