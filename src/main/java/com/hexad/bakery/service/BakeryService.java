package com.hexad.bakery.service;

import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;

import java.util.List;


public class BakeryService {

    private InventoryService inventoryService;
    private ProductService productService;

    public BakeryService(InventoryService inventoryService, ProductService productService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    public Invoice processOrder(List<Order> orders) {
        Invoice invoice = new Invoice();
        for (Order order : orders) {
            int availableQuantity = inventoryService.getAvailableQuantities(order.getCode());
            if (order.getQuantity() <= availableQuantity) {
                invoice.addEntry(order, productService.decidePacksForOrder(order));
            } else {
                throw new RuntimeException("Unavailable Quantity");
            }
        }
        return invoice;
    }
}
