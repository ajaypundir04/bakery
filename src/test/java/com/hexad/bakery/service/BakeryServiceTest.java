package com.hexad.bakery.service;

import com.hexad.bakery.dto.Results;
import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Pack;
import com.hexad.bakery.models.Product;
import com.hexad.bakery.service.impl.BakeryServiceImpl;
import com.hexad.bakery.service.impl.InventoryService;
import com.hexad.bakery.service.impl.ProductServiceImpl;
import com.hexad.bakery.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BakeryServiceTest {

    private BakeryServiceImpl bakeryService = BakeryServiceImpl.getInstance();

    @Before
    public void setup() throws Exception {
        InventoryService inventoryService = new InventoryService();

        Product[] products = TestUtils.readJsonFile("test-data.json", Product[].class);
        Arrays.stream(products).forEach(p -> inventoryService.addOrUpdateInventory(p, 30));

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        bakeryService.setInventoryService(inventoryService);
        bakeryService.setProductServiceImpl(productServiceImpl);
    }

    @Test
    public void processOrder() throws Exception {
        Order[] orders = TestUtils.readJsonFile("test-orders.json", Order[].class);
        Results[] results = TestUtils.readJsonFile("expected-results.json", Results[].class);

        Invoice invoice = bakeryService.processOrder(Arrays.asList(orders));
        assertNotNull(invoice);
        assertNotNull(invoice.allOrders());
        assertEquals(results.length, invoice.allOrders().size());
        for (Results result : results) {
            Order order = getOrderForCode(result.getCode(), invoice);
            assertNotNull(order);
            Map<Pack, Integer> packs = invoice.getPacksForOrder(order);
            assertEquals(result.getPacks().size(), packs.size());
            for (Results.ExpectedPack pack : result.getPacks()) {
                Results.ExpectedPack actualResult = packs.entrySet().stream()
                        .filter(e -> e.getKey().getQuantity() == pack.getPack())
                        .map(e -> new Results.ExpectedPack(e.getKey().getQuantity(), e.getValue()))
                        .findFirst()
                        .orElse(null);
                assertNotNull(actualResult);
                assertEquals(pack.getPack(), actualResult.getPack());
                assertEquals(pack.getCount(), actualResult.getCount());
            }
        }
    }

    private Order getOrderForCode(String code, Invoice invoice) {
        return invoice.allOrders()
                .stream()
                .filter(order -> order.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}