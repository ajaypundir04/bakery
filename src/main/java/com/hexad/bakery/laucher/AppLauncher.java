package com.hexad.bakery.laucher;

import com.hexad.bakery.model.*;
import com.hexad.bakery.service.OrderProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppLauncher {

    public static void main(String [] args)
    {
        OrderProcessor op=OrderProcessor.getInstance();
        Inventory inventory=loadInventory();
        List<ItemOrder> itemOrderList=getOrder();
        List<Bill> bills=op.processOrder(itemOrderList,inventory);
        bills.forEach(
                bill -> {
                    System.out.println(bill);
                }
        );
    }

    private static Inventory loadInventory() {
        Product product=new Product();
        product.setName("Vegemite Scroll");
        product.setCode("VS5");
        List<Packs> packsList=new ArrayList<>();
        Packs pack=new Packs();
        pack.setPrice(6.99);
        pack.setQuantity(3);
        packsList.add(pack);
        pack=new Packs();
        pack.setQuantity(5);
        pack.setPrice(8.99);
        packsList.add(pack);
        product.setPacks(packsList);
        Inventory inventory=new Inventory();
        Map<String,Product> productMap=new HashMap<>();
        productMap.put("VS5",product);
        inventory.setProducts(productMap);
        return inventory;
    }

    private static  List<ItemOrder> getOrder()
    {
        List<ItemOrder> itemOrders=new ArrayList<>();
        ItemOrder itemOrder=new ItemOrder();
        itemOrder.setProductCode("VS5");
        itemOrder.setQuantity(10);
        itemOrders.add(itemOrder);
        return itemOrders;
    }
}
