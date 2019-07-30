package com.hexad.bakery.laucher;

import com.hexad.bakery.constants.ApplicationConstant;
import com.hexad.bakery.util.FileReader;
import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Product;
import com.hexad.bakery.service.impl.BakeryServiceImpl;
import com.hexad.bakery.service.impl.InventoryService;
import com.hexad.bakery.service.impl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * @author Ajay Singh Pundir
 * Intial Point of Application
 */
@SpringBootApplication
public class ApplicationLauncher {

    private static BakeryServiceImpl bakeryService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationLauncher.class, args);
        applicationContext.registerShutdownHook();

        bakeryService = initialize();
        List<Order> orders = FileReader.loadOrder("orders.json", ApplicationConstant.JSON_EXTENSION);
        Invoice invoice = bakeryService.processOrder(orders);
        invoice.prettyPrint();
        applicationContext.close();
    }

    private static BakeryServiceImpl initialize() throws IOException {
        Product [] products = FileReader.loadProduct("products.json", ApplicationConstant.JSON_EXTENSION);
        InventoryService inventoryService = new InventoryService();
        for (Product p : products) {
            inventoryService.addOrUpdateInventory(p, ApplicationConstant.DEFAULT_QUANTITY);
        }
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        BakeryServiceImpl bakeryService = BakeryServiceImpl.getInstance();
        bakeryService.setInventoryService(inventoryService);
        bakeryService.setProductServiceImpl(productServiceImpl);
        return bakeryService;
    }

}
