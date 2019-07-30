package com.hexad.bakery.service.impl;

import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Pack;
import com.hexad.bakery.models.Product;
import com.hexad.bakery.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * It is used to perform Product related operations
 */
public class ProductServiceImpl implements ProductService {

    private InventoryService inventoryService;

    public ProductServiceImpl(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * @param productCode
     * @param newPack     It is used to add new Pack for a particular product
     */
    @Override
    public void registerNewPack(String productCode, Pack newPack) {
        Product product = inventoryService.getProductByCode(productCode);
        product.getPacks().add(newPack);
        inventoryService.addOrUpdateInventory(product, 0);
    }

    /**
     * @param order It will accept the order
     * @return On the basis of Order it will return the minimum List @{@link Pack} of @{@link Product}
     */
    @Override
    public List<Pack> decidePacksForOrder(Order order) {
        Product product = inventoryService.getProductByCode(order.getCode());
        return getProductPack(product.getPacks(), order.getQuantity());
    }

    private List<Pack> getProductPack(Set<Pack> availablePacks, int requiredQuantity) {
        List<Pack> packs = new ArrayList<>(availablePacks);
        List<Pack> result = new ArrayList<>();
        int quantity = requiredQuantity;
        List<Integer> quantityList = packs.stream().map(Pack::getQuantity).collect(Collectors.toList());
        for (int i = packs.size() - 1; i >= 0; i--) {
            while (quantity >= packs.get(i).getQuantity()) {
                int remain = quantity - packs.get(i).getQuantity();
                if (remain >= packs.get(i).getQuantity() || isPickAble(remain, quantityList)) {
                    quantity -= packs.get(i).getQuantity();
                    result.add(packs.get(i));
                } else {
                    quantityList.remove((Integer) packs.get(i).getQuantity());
                    break;
                }
            }
        }

        return result;
    }

    private boolean isPickAble(int remain, List<Integer> quantityList) {
        return (quantityList
                .stream()
                .filter(i -> (remain % i == 0))
                .collect(Collectors.toList())
                .size() != 0);
    }
}
