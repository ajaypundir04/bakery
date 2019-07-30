package com.hexad.bakery.service.impl;

import com.hexad.bakery.constants.ApplicationConstant;
import com.hexad.bakery.exception.BakeryServiceException;
import com.hexad.bakery.models.Invoice;
import com.hexad.bakery.models.Order;
import com.hexad.bakery.service.BakeryService;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * This class acts a interface for processing the order
 */
public class BakeryServiceImpl implements BakeryService {

    private static BakeryServiceImpl instance = new BakeryServiceImpl();

    private InventoryService inventoryService;
    private ProductServiceImpl productServiceImpl;

    private BakeryServiceImpl() {
    }

    public static BakeryServiceImpl getInstance() {
        return instance;
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
                invoice.addEntry(order, productServiceImpl.decidePacksForOrder(order,inventoryService.getProductByCode(order.getCode())));
            } else {
                throw new BakeryServiceException(ApplicationConstant.UNAVAILABLE_QUANTITY);
            }
        }
        return invoice;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public ProductServiceImpl getProductServiceImpl() {
        return productServiceImpl;
    }

    public void setProductServiceImpl(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }
}
