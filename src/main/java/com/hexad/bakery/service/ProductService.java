package com.hexad.bakery.service;

import com.hexad.bakery.models.Order;
import com.hexad.bakery.models.Pack;
import com.hexad.bakery.models.Product;

import java.util.List;

public interface ProductService {
    List<Pack> decidePacksForOrder(Order order, Product product);
}
