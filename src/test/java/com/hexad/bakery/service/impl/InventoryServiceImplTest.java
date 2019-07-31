package com.hexad.bakery.service.impl;

import com.hexad.bakery.models.Pack;
import com.hexad.bakery.models.Product;
import com.hexad.bakery.utils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class InventoryServiceImplTest {
    InventoryService inventoryService;
    Product[] products;

    @Before
    public void setup() throws Exception {
        inventoryService = new InventoryService();
        products = TestUtils.readJsonFile("test-data.json", Product[].class);
    }

    @Test
    public void addOrUpdateInventoryTest() {
        Arrays.stream(products).forEach(p -> inventoryService.addOrUpdateInventory(p, 30));
        Assert.assertEquals(30, inventoryService.getAvailableQuantities("MB11"));
        Assert.assertEquals(30, inventoryService.getAvailableQuantities("VS5"));
        Assert.assertEquals(30, inventoryService.getAvailableQuantities("CF"));
    }

    @Test
    public void registerNewPackTest() {
        Pack pack = new Pack(5, 8.66f);
        Arrays.stream(products).forEach(p -> inventoryService.addOrUpdateInventory(p, 30));
        inventoryService.registerNewPack("CF", pack);
        Product product = inventoryService.getProductByCode("CF");
        Assert.assertEquals(3, product.getPacks().size());
    }


    @Test
    public void removeProductFromInventoryTest() {
        Arrays.stream(products).forEach(p -> inventoryService.addOrUpdateInventory(p, 30));
        Product product = inventoryService.getProductByCode("CF");
        Assert.assertNotNull(product);
        inventoryService.removeProductFromInventory(product);
        Assert.assertEquals(0, inventoryService.getAvailableQuantities("CF"));
    }


}
