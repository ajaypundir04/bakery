package com.hexad.bakery.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Invoice {

    // order -> [packs]
    private Map<Order, Map<Pack, Integer>> items;

    public Invoice() {
        items = new HashMap<>();
    }

    public void addEntry(Order order, List<Pack> packs) {
        if (!items.containsKey(order)) {
            items.put(order, new HashMap<>());
        }
        Map<Pack, Integer> tmpMap = items.get(order);
        packs.forEach(pack -> {
            if (!tmpMap.containsKey(pack)) {
                tmpMap.put(pack, 0);
            }
            tmpMap.put(pack, tmpMap.get(pack) + 1);
        });
    }

    public Collection<Order> allOrders() {
        return items.keySet();
    }

    public void prettyPrint() {
        allOrders().forEach(this::prettyPrint);
    }

    public void prettyPrint(Order order) {
        Map<Pack, Integer> priceDetail = items.get(order);
        double totalPrice = priceDetail.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .map(value -> roundOff(value, 2))
                .sum();
        System.out.println(order.getQuantity() + " " + order.getCode() +" $"+totalPrice);
        priceDetail.forEach((pack, count) -> System.out.println(count + " x " + pack.toString()));
    }

    private double roundOff(double value, int decimalPlaces) {
        return new BigDecimal(value).setScale(decimalPlaces, RoundingMode.UP).doubleValue();
    }


    public Map<Pack, Integer> getPacksForOrder(Order order) {
        return items.get(order);
    }
}
