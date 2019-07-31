package com.hexad.bakery.service.impl;

import com.hexad.bakery.constants.ApplicationConstant;
import com.hexad.bakery.exception.BakeryServiceException;
import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;
import com.hexad.bakery.service.BakeryService;
import com.hexad.bakery.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * This class acts a interface for processing the order
 */
@Service
public class BakeryServiceImpl implements BakeryService {

    private ProductService productService;
    private final InventoryService inventoryService;

    private BakeryServiceImpl(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    /**
     * @param orders List of @{@link Order} will be passed as an arguments
     * @return @{@link Invoice} calculated bills for each and every order processed
     */
    @Override
    public Invoice processOrder(List<Order> orders) {
        Invoice invoice = new Invoice();
        for (Order order : orders) {
            int availableQuantity = inventoryService.getAvailableQuantities(order.getCode());
            if (order.getQuantity() <= availableQuantity) {
                invoice.addEntry(order, productService.decidePacksForOrder(order,inventoryService.getProductByCode(order.getCode())));
            } else {
                throw new BakeryServiceException(ApplicationConstant.UNAVAILABLE_QUANTITY);
            }
        }
        return invoice;
    }
}
