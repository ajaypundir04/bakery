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
    /**
     * @param order It will accept the order
     * @return On the basis of Order it will return the minimum List @{@link Pack} of @{@link Product}
     */
    @Override
    public List<Pack> decidePacksForOrder(Order order, Product product) {
        return getProductPack(product.getPacks(), order.getQuantity());
    }

    private List<Pack> getProductPack(Set<Pack> availablePacks, int requiredQuantity) {
        List<Pack> packs = new ArrayList<>(availablePacks);
        List<Pack> result = new ArrayList<>();
        int quantity = requiredQuantity;
        List<Integer> quantityList = packs.stream().map(Pack::getQuantity).collect(Collectors.toList());
        for (int i = packs.size() - 1; i >= 0; i--) {
            if (quantity >= packs.get(i).getQuantity()) {
                int remain = quantity - packs.get(i).getQuantity();
                if (remain >= packs.get(i).getQuantity() || isPickAble(remain, quantityList)) {
                    quantity -= packs.get(i).getQuantity();
                    result.add(packs.get(i));
                    i++;
                } else {
                    quantityList.remove((Integer) packs.get(i).getQuantity());
                }
            }
        }

        return result;
    }

    private boolean isPickAble(int remain, List<Integer> quantityList) {
        return (quantityList
                .stream()
                .anyMatch(i -> (remain % i == 0)));
    }
}
