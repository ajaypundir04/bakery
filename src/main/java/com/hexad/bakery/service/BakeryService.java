package com.hexad.bakery.service;

import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;

import java.util.List;

public interface BakeryService {
    Invoice processOrder(List<Order> orders);
}
