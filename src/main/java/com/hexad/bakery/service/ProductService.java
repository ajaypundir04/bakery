package com.hexad.bakery.service;

import com.hexad.bakery.entities.Pack;
import com.hexad.bakery.entities.Product;
import com.hexad.bakery.models.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductService {

    List<Pack> decidePacksForOrder(Order order, Product product);
}
