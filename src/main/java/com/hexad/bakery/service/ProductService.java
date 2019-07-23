package com.hexad.bakery.service;

import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Pack;
import com.hexad.bakery.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ProductService {

    private InventoryService inventoryService;

    public ProductService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void registerNewPack(String productCode, Pack newPack) {
        Product product = inventoryService.getProductByCode(productCode);
        product.getPacks().add(newPack);
        inventoryService.addOrUpdateInventory(product, 0);
    }

    public List<Pack> decidePacksForOrder(Order order) {
        Product product = inventoryService.getProductByCode(order.getCode());
        return getProductPack(product.getPacks(), order.getQuantity());
    }

    private List<Pack> getProductPack(Set<Pack> availablePacks, int requiredQuantity) {

        List<Pack> packs = new ArrayList<>(availablePacks);
        List<Pack> result = new ArrayList<>();
        int quantity = requiredQuantity;
        List<Integer> quantityList =packs.stream().map(Pack::getQuantity).collect(Collectors.toList());
        for (int i = packs.size() - 1; i >= 0; i--)
        {
            while (quantity >= packs.get(i).getQuantity())
            {
                int remain=quantity-packs.get(i).getQuantity();
                if(isPickAble(remain,quantityList)||remain>=packs.get(i).getQuantity())
                {
                    quantity -= packs.get(i).getQuantity();
                    result.add(packs.get(i));
                }
                else
                {
                    quantityList.remove(quantityList.indexOf(packs.get(i).getQuantity()));
                    break;
                }
            }
        }

        return result;
    }

    private boolean isPickAble(int remain, List<Integer> quantityList)
    {
        return (quantityList
                .stream()
                .filter(i -> (remain%i==0))
                .collect(Collectors.toList())
                .size()!=0);
    }
}
