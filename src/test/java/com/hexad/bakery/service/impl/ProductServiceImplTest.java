package com.hexad.bakery.service.impl;

import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Pack;
import com.hexad.bakery.models.Product;
import com.hexad.bakery.utils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductServiceImplTest {

    InventoryService inventoryService;
    Product product;
    Order order;

    @Before
    public void setUp() throws Exception {
        inventoryService = new InventoryService();
        product = TestUtils.readJsonFile("product.json", Product.class);
        inventoryService.addOrUpdateInventory(product, 30);
        order = TestUtils.readJsonFile("order.json", Order.class);
    }

    @Test
    public void decidePacksForOrderTest() {
        ProductServiceImpl productService = new ProductServiceImpl();
        List<Pack> packList = productService.decidePacksForOrder(order, product);
        Assert.assertNotNull(packList);
        Assert.assertEquals(4, packList.size());
        float total = 0.0f;
        for (Pack p : packList)
            total += p.getPrice();
        Assert.assertEquals(84.8f, total, 0.10);
    }

}
