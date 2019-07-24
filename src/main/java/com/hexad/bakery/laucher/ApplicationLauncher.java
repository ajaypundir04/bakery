package com.hexad.bakery.laucher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Product;
import com.hexad.bakery.service.BakeryService;
import com.hexad.bakery.service.InventoryService;
import com.hexad.bakery.service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class ApplicationLauncher {

    private static BakeryService bakeryService;
    private static final int DEFAULT_QUANTITY = 30;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        bakeryService = initialize();

        List<Order> orders = readOrders();

        Invoice invoice = bakeryService.processOrder(orders);
        invoice.prettyPrint();
    }

    private static BakeryService initialize() throws IOException {
        Product[] products = loadData();
        InventoryService inventoryService = new InventoryService();
        for (Product p : products) {
            inventoryService.addOrUpdateInventory(p, DEFAULT_QUANTITY);
        }
        ProductService productService = new ProductService(inventoryService);
        return new BakeryService(inventoryService, productService);
    }

    private static Product[] loadData() throws IOException {
        return MAPPER.readValue(resourceFileAsUrl("data.json"), Product[].class);
    }

    private static List<Order> readOrders() throws IOException {
        return MAPPER.readValue(resourceFileAsUrl("orders.json"), new TypeReference<List<Order>>() {
        });
    }

    private static URL resourceFileAsUrl(String file) {
        return ApplicationLauncher.class.getClassLoader().getResource(file);
    }
}
