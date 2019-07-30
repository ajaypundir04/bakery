package com.hexad.bakery.service;

import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Pack;

import java.util.List;

public interface ProductService {
    void registerNewPack(String productCode, Pack newPack);
    List<Pack> decidePacksForOrder(Order order);
}
